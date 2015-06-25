package network.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
import javax.mail.event.MessageChangedEvent;
import javax.mail.event.MessageChangedListener;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;

import network.messageFramework.DeliverySystem;
import network.messageFramework.FrameworkMessage;

import com.sun.mail.imap.IMAPFolder;

public class storeTest {
	public static void main(String[] args) throws Exception {
		Properties props = System.getProperties();
        // Get a Session object
        Session session = Session.getInstance(props, null);
        session.setDebug(true);
        Store store = session.getStore("imaps");
        
        // Ouvrir la connexion
        store.connect("imap.gmail.com", "address@gmail.com","password");

        IMAPFolder folder = (IMAPFolder) store.getDefaultFolder();
        
        FolderManager manager = new FolderManager(folder);
        manager.startSync();
	}
}

class FolderManager {
	private IMAPFolder mainFolder;
	private List<IMAPFolder> folders;

	public FolderManager(IMAPFolder folder) {
		this.mainFolder = folder;
		this.folders = new ArrayList<>();
	}

	public void startSync() {
		try {
			initFolders();
			
			ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
			executor.scheduleAtFixedRate(new Runnable() {
				
				@Override
				public void run() {
					System.out.println("<<<<<<<<<<<<<<<<<<<< in");
					for(IMAPFolder f: folders) {
						DeliverySystem.launchTask(new FolderSynchronizationRequest(f));
					}
					System.out.println("<<<<<<<<<<<<<<<<<<<< booub");
				}
				
			}, 0, 3, TimeUnit.SECONDS);
			
			
		} catch (MessagingException e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while opening folder", e);
		}
	}

	private void initFolders() throws MessagingException {
		for(Folder f : mainFolder.list()) {
			if(open(f)) {
				folders.add((IMAPFolder) f);
				System.out.println(">>>>>>>>>>>>>" + folders.get(folders.size() - 1).getName());
			}
		}
		initFolderListners();
	}

	private boolean open(Folder folder) throws MessagingException {
		if((folder.getType() & Folder.HOLDS_MESSAGES) == Folder.HOLDS_MESSAGES) {
			if(!folder.isOpen()) folder.open(Folder.READ_WRITE);
			return true;
		}
		return false;
	}

	private void initFolderListners() {
		SuperFolderListener superListener = new SuperFolderListener();
		addListeners(mainFolder, superListener);
		for(IMAPFolder f : folders) {
			addListeners(f, superListener);
		}
	}

	private void addListeners(IMAPFolder folder, SuperFolderListener superListener) {
		folder.addConnectionListener(superListener);
		folder.addFolderListener(superListener);
		folder.addMessageCountListener(superListener);
		folder.addMessageChangedListener(superListener);
	}

	class SuperFolderListener implements ConnectionListener, MessageChangedListener, MessageCountListener, FolderListener {

		@Override
		public void messagesAdded(MessageCountEvent arg0) {
			System.out.println("messagesAdded");
		}

		@Override
		public void messagesRemoved(MessageCountEvent arg0) {
			System.out.println("messagesRemoved");
		}

		@Override
		public void messageChanged(MessageChangedEvent arg0) {
			System.out.println("messageChanged");
		}

		@Override
		public void closed(ConnectionEvent arg0) {
			System.out.println("closed");
		}

		@Override
		public void disconnected(ConnectionEvent arg0) {
			System.out.println("disconnected");
		}

		@Override
		public void opened(ConnectionEvent arg0) {
			System.out.println("ConnectionEvent");
		}

		@Override
		public void folderCreated(FolderEvent arg0) {
			System.out.println("FolderEvent");
		}

		@Override
		public void folderDeleted(FolderEvent arg0) {
			System.out.println("folderDeleted");
		}

		@Override
		public void folderRenamed(FolderEvent arg0) {
			System.out.println("folderRenamed");	
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
			}
			catch(MessagingException e) {
				Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occured while synchronizing folder", e);
			}
			return null;
		}
	}
}
