package network.mail;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Store;
import javax.mail.event.ConnectionListener;
import javax.mail.event.FolderListener;
import javax.mail.event.MessageChangedListener;
import javax.mail.event.MessageCountListener;

import model.MailAccount;
import network.messageFramework.AbstractSender;
import network.messageFramework.DeliverySystem;
import network.messageFramework.FrameworkMessage;

import com.sun.mail.imap.IMAPFolder;
import cookie.swipe.application.CookieSwipeApplication;
import javax.mail.Address;
import javax.mail.Message;
import model.User;

public class FolderManager {

    private static final int preloadedMessagesCount = 30;
    private Map<IMAPFolder, Entry<Store, MailAccount>> folders;
    private ScheduledExecutorService synchronizer;

    public FolderManager() {
        this.folders = new ConcurrentHashMap<>();
        this.synchronizer = Executors.newSingleThreadScheduledExecutor();
    }

    public void addMailAccounts(Collection<MailAccount> mailAccounts) {

        AbstractSender<Object> sender = new AbstractSender<Object>() {
            @Override
            public void onMessageReceived(Future<Object> receivedMessage) {
            }

            @Override
            public void onAllMessagesReceived() {
                start();
            }
        };

        List<FrameworkMessage<?>> sentMessages = new ArrayList<>();

        for (MailAccount mc : mailAccounts) {
            sentMessages.add(new FrameworkMessage<Object>() {
                private static final long serialVersionUID = 5861187087219031673L;

                @Override
                public Object call() throws Exception {
                    try {
                        addMailAccount(mc);
                    } catch (Exception e) {
                        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while opening store", e);
                    }
                    return null;
                }
            });
        }

        sender.sendMessages(sentMessages);
    }

    private void addMailAccount(MailAccount mc) {
        try {
            Store store = mc.getClientConnection();
            for (Folder f : store.getDefaultFolder().list()) {
                if (open(f)) {
                    folders.put((IMAPFolder) f, new AbstractMap.SimpleEntry<Store, MailAccount>(store, mc));
                    addListeners((IMAPFolder) f, mc);
                    mc.createModelFor(f.getName());
                }
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while opening folder", e);
        }
    }

    public boolean addNewMailAccount(MailAccount mc) {
        try {
            addMailAccount(mc);
            retrieveMailsFor(mc);
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while opening store", e);
            return false;
        }
        return true;
    }

    public void removeMailAccount(MailAccount mc) {
        List<IMAPFolder> foldersToRemove = new ArrayList<>();
        for (IMAPFolder f : folders.keySet()) {
            if (folders.get(f).getValue().equals(mc)) {
                foldersToRemove.add(f);
            }
        }
        removeFolders(foldersToRemove);
    }

    private void removeFolders(Collection<IMAPFolder> foldersToRemove) {
        removeFolderListener(foldersToRemove);
        closeFolders(foldersToRemove);
        for (IMAPFolder f : foldersToRemove) {
            folders.remove(f);
        }
    }

    private void removeFolderListener(Collection<IMAPFolder> foldersToRemove) {
        for (IMAPFolder f : foldersToRemove) {
            removeListeners(f, folders.get(f).getValue());
        }
    }

    private void closeFolders(Collection<IMAPFolder> foldersToRemove) {
        for (IMAPFolder f : foldersToRemove) {
            try {
                folders.get(f).getKey().close();
            } catch (MessagingException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while closing folder", e);
            }
        }
    }

    private void start() {
        retrieveMails();
        startSync();
    }

    private void retrieveMailsFor(MailAccount mc) {
        List<IMAPFolder> newFolders = new ArrayList<>();
        for (Folder f : folders.keySet()) {
            if (folders.get(f).getValue().equals(mc)) {
                newFolders.add((IMAPFolder) f);
            }
        }
        DeliverySystem.launchTask(new FrameworkMessage<Object>() {

            private static final long serialVersionUID = 7108005018953413117L;

            @Override
            public Object call() throws Exception {
                Map<IMAPFolder, Integer> lengths = new HashMap<>();
                int index = 0;

                for (IMAPFolder f : newFolders) {
                    lengths.put(f, f.getMessageCount());
                }

                for (int i = 0; i < preloadedMessagesCount; ++i) {
                    for (IMAPFolder f : newFolders) {
                        index = lengths.get(f) - i;
                        if (index > 0) {
                            mc.addToListOfmail(f.getName(), f.getMessage(index));
                        }
                    }
                }
                return null;
            }

        });
    }

    private void retrieveMails() {
        try {
            Map<IMAPFolder, Integer> lengths = new HashMap<>();

            for (IMAPFolder f : folders.keySet()) {
                lengths.put(f, f.getMessageCount());
            }

            for (int i = 0; i < preloadedMessagesCount; ++i) {
                DeliverySystem.launchTask(new RetreiveMailMessage(lengths, i));
            }
        } catch (MessagingException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while retreiving mails", e);
        }
    }

    class RetreiveMailMessage extends FrameworkMessage<Object> {

        private static final long serialVersionUID = 3123849369769712930L;

        private Map<IMAPFolder, Integer> lengths;
        private int i;

        RetreiveMailMessage(Map<IMAPFolder, Integer> lengths, int i) {
            this.lengths = lengths;
            this.i = i;
        }

        @Override
        public Object call() throws Exception {
            for (IMAPFolder f : folders.keySet()) {
                int index = lengths.get(f) - i;
                if (index > 0) {
                    MailAccount mc = folders.get(f).getValue();
                    mc.addToListOfmail(f.getName(), f.getMessage(index));
                }
            }
            return null;
        }
    }

    public void closeSync() {
        stopSync();
        removeFolders(folders.keySet());
    }

    public void stopSync() {
        synchronizer.shutdown();
    }

    public void startSync() {
        synchronizer.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                refresh();
            }
        }, 0, 60, TimeUnit.SECONDS);
    }
    
    public void refresh() {
        for (IMAPFolder f : folders.keySet()) {
            DeliverySystem.launchTask(new FolderSynchronizationRequest(f));
        }
    }

    private boolean open(Folder folder) throws MessagingException {
        if ((folder.getType() & Folder.HOLDS_MESSAGES) == Folder.HOLDS_MESSAGES) {
            if (!folder.isOpen()) {
                folder.open(Folder.READ_WRITE);
            }
            return true;
        }
        return false;
    }

    public void addListeners(IMAPFolder folder, Object listener) {
        if (listener instanceof ConnectionListener) {
            folder.addConnectionListener((ConnectionListener) listener);
        }
        if (listener instanceof FolderListener) {
            folder.addFolderListener((FolderListener) listener);
        }
        if (listener instanceof MessageCountListener) {
            folder.addMessageCountListener((MessageCountListener) listener);
        }
        if (listener instanceof MessageChangedListener) {
            folder.addMessageChangedListener((MessageChangedListener) listener);
        }
    }

    public void removeListeners(IMAPFolder folder, Object listener) {
        if (listener instanceof ConnectionListener) {
            folder.removeConnectionListener((ConnectionListener) listener);
        }
        if (listener instanceof FolderListener) {
            folder.removeFolderListener((FolderListener) listener);
        }
        if (listener instanceof MessageCountListener) {
            folder.removeMessageCountListener((MessageCountListener) listener);
        }
        if (listener instanceof MessageChangedListener) {
            folder.removeMessageChangedListener((MessageChangedListener) listener);
        }
    }

    class FolderSynchronizationRequest extends FrameworkMessage<Object> {

        private static final long serialVersionUID = 764857642308858407L;
        private IMAPFolder folder;

        public FolderSynchronizationRequest(IMAPFolder folder) {
            this.folder = folder;
        }

        @Override
        public Boolean call() throws Exception {
            try {
                folder.getMessageCount();
            } catch (MessagingException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while synchronizing folder", e);
            }
            return null;
        }
    }
}
