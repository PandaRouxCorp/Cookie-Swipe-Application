/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
import javax.mail.event.MessageChangedEvent;
import javax.mail.event.MessageChangedListener;
import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.SwingUtilities;

import module.ihm.CustomJListModel;
import module.ihm.MainFrameInitializer;
import network.messageFramework.AbstractSender;
import network.messageFramework.FrameworkMessage;

import com.sun.mail.imap.IMAPFolder;

import cookie.swipe.application.CookieSwipeApplication;
import cookie.swipe.application.utils.LinkedHashSetPriorityQueueObserver;
import cookie.swipe.application.utils.ObservableLinkedHashSetPriorityQueue;
import errorMessage.CodeError;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import javax.swing.JOptionPane;

/**
 *
 * @author Mary
 */
public class MailAccount implements ConnectionListener, MessageChangedListener, MessageCountListener, FolderListener {

    public static final String ALL = "Tous";

    // Variable membre
    private int id;
    private String address, CSName, password, color, mailSignature;
    private Domain domain;
    private Date lastSynch;
    private Session session;
    private Message currentMessage;
    private HashMap<String, ObservableLinkedHashSetPriorityQueue<Message>> folderListModels;
    private List<String> folderNames;
    private List<File> attachments;
    private Multipart multipart;
    private MimeBodyPart messageBodyPart;

    // Constructeur
    /**
     * Constructeur par defaut
     */
    public MailAccount() {
        domain = new Domain();
        this.folderNames = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.folderListModels = new HashMap<>();
    }

    /**
     * Constructeur a utiliser lors de l'ajout d'une boite courriel
     *
     * @param CSName nom donne a la botie courriel
     * @param address adresse courriel de la boite
     * @param password mot de passe pour acceder a la boite courriel
     * @param color couleur donne a la boite courriel pour l'affichage des
     * courriels
     */
    public MailAccount(String CSName, String address, Domain domain,
            String password, String color) {

        this.CSName = CSName;
        this.address = address;
        this.domain = domain;
        try {
            this.password = new Encryption().encrypt(password);
        } catch (Exception ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        this.color = color;
        this.folderNames = new ArrayList<>();
        this.folderListModels = new HashMap<>();
    }

    /**
     * Constructeur a utiliser pour charger les comptes courriel dans
     * l'application
     *
     * @param id identifiant unique de la boite mail
     * @param CSName nom donne a la boite courriel
     * @param address adresse courriel de la boite
     * @param password mot de passe pour acceder a la boite mail
     * @param color couleur donne a la boite courriel pour l'affichage des
     * courriels
     * @param domain domaine de l'adresse courriel
     * @param mailSignature signature a mettre a la fin d'un courriel
     * @param lastSynch date de la derniere synchronisation reussi de la boite
     * courriel
     * @param listOfMail Liste des courriels de la boite mail
     */
    public MailAccount(int id, String CSName, String address, String password,
            String color, Domain domain, String mailSignature, Date lastSynch,
            Collection<Message> listOfMail) {
        this.id = id;
        this.address = address;
        this.CSName = CSName;
        try {
            this.password = new Encryption().encrypt(password);
        } catch (Exception ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        this.color = color;
        this.domain = domain;
        this.mailSignature = mailSignature;
        this.lastSynch = lastSynch;
        this.folderNames = new ArrayList<>();
        this.folderListModels = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void createModelFor(String folder) {
        this.folderNames.add(folder);

        ObservableLinkedHashSetPriorityQueue<Message> observableList
                = new ObservableLinkedHashSetPriorityQueue<>(new MailComparator());

        folderListModels.put(folder, observableList);

        HashMap<String, HashMap<String, CustomJListModel>> models
                = (HashMap<String, HashMap<String, CustomJListModel>>) CookieSwipeApplication.getApplication().getParam("jListMailModels");

        HashMap<String, CustomJListModel> jListModels = models.get(getCSName());

        if (jListModels == null) {
            jListModels = new HashMap<>();
            models.put(getCSName(), jListModels);
        }

        jListModels.put(folder, new CustomJListModel(observableList));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrameInitializer().addFolderInTree(MailAccount.this, folder);
            }
        });
    }

    public boolean connectionIsOk() {
        try {
            getClientConnection();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Store getClientConnection() throws MessagingException, Exception {
        // Get a Properties object
        Properties props = System.getProperties();

        // Get a Session object
        Session session = Session.getInstance(props, null);
        session.setDebug(false);

        Store store = session.getStore(domain.getStoreProtocol());

        // Ouvrir la connexion
        store.connect(domain.getServerIn(), address, new Encryption().decrypt(password));

        return store;
    }

    public Properties getProperties() {
        Properties props = new Properties();

        props.put("mail.transport.protocol", domain.getStoreProtocol());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", domain.getServerOut());
        props.put("mail.smtp.socketFactory.port", domain.getPortOut());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.user", address);
        props.put("mail.smtp.port", domain.getPortOut());
        props.put("mail.from", domain.getServerIn());
        return props;
    }

    public Session getSession() {
        if (session == null) {
            session = Session.getDefaultInstance(getProperties(),
                    new javax.mail.Authenticator() {
                        @Override
                        public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            String pwd = "";
                            try {
                                pwd = new Encryption().decrypt(password);
                            } catch (Exception ex) {
                                Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            return new javax.mail.PasswordAuthentication(address, pwd);
                        }
                    }
            );
        }
        return session;
    }

    /**
     * Sert a creer un nouveau courriel
     *
     * @return courriel cree
     */
    public void createNewMail() {
        messageBodyPart = new MimeBodyPart();
        currentMessage = new MimeMessage(getSession());
    }

    public void addDestinataire(Address[] destinataire) {
        try {
            currentMessage.addRecipients(Message.RecipientType.TO, destinataire);
        } catch (MessagingException ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addDestinataire(String destinataire) {
        try {
            currentMessage.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
        } catch (MessagingException ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addSubject(String subject) {
        try {
            currentMessage.setSubject(subject);
        } catch (MessagingException ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearAttachments() {
        attachments.clear();
    }

    public void addBody(String body) {
        try {
            if(attachments.size() > 0)
                messageBodyPart.setText(body);
            else
                currentMessage.setText(body);
        } catch (MessagingException ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCopie(String copy) {
        try {
            currentMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse(copy));
        } catch (MessagingException ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sert a envoyer un courriel, delegue l'envoi a un domaine )
     */
    public void sendMail() {
        System.out.println("pj length : " + attachments.size());
        AbstractSender<Boolean> s = new AbstractSender<Boolean>() {
            @Override
            public void onMessageReceived(Future<Boolean> receivedMessage) {
                try {
                    boolean sended = receivedMessage.get();
                    int error;
                    if (sended) {
                        error = CodeError.SUCESS;
                    } else {
                        error = CodeError.CONNEXION_FAIL;
                    }
                    switch (error) {
                        case CodeError.SUCESS:
                            JOptionPane.showMessageDialog(null, "Votre compte mail à bien été envoyé",
                                    "Envoi d'un mail", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case CodeError.CONNEXION_FAIL:
                            JOptionPane.showMessageDialog(null, "Problème de connexion \nCode erreur : " + error,
                                    "Envoi d'un mail", JOptionPane.ERROR_MESSAGE);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                                    "Envoi d'un mail", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        
        s.sendMessage(new FrameworkMessage< Boolean>() {
            @Override
            public Boolean call() throws Exception {

                try {
                    if ( attachments.size() > 0 ) {
                        multipart = getMultiPart();
                        if(multipart != null)
                            currentMessage.setContent(multipart);
                    }

                    Transport.send(currentMessage);
                    return true;
                } catch (MessagingException e) {
                }
                return false;
            }
        });
    }
    
    public Multipart getMultiPart() {
        if( multipart != null )
            return multipart;
        
        try {
            multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            for (File file : attachments) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource( file );
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName( file.getName() );
                multipart.addBodyPart(messageBodyPart);
            }
            return multipart;
        } catch (MessagingException ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setMessage(Message mess) {
        currentMessage = mess;
    }

    /**
     * Sert a supprimer un courriel de la boite courriel
     *
     * @param mail courriel a supprimer
     * @return Si la suppresion c'est bien passe
     */
    public boolean deleteMail(Mail mail) {
        return false;
    }

    public void addAttachements(File[] files) {
        attachments.addAll(Arrays.asList(files));
    }
    public void addAttachement(File file) {
        attachments.add(file);
    }

    public void addAttachement(String filename) {
        attachments.add(new File(filename));
    }

    // Getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public int setAddress(String address) {
        String mailDomain = address.substring(address.indexOf('@') + 1);
        try {
            domain = Domain.getDomainFor(mailDomain);
        } catch (Exception ex) {
            Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, "", ex);
            return CodeError.FAILLURE;
        }
        this.address = address;
        return CodeError.SUCESS;
    }

    public String getCSName() {
        return CSName;
    }

    public void setCSName(String CSName) {
        this.CSName = CSName;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMailSignature() {
        return mailSignature;
    }

    public void setMailSignature(String mailSignature) {
        this.mailSignature = mailSignature;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getLastSynch() {
        return lastSynch;
    }

    public void setLastSynch(Date lastSynch) {
        this.lastSynch = lastSynch;
    }

    public Mail[] getListOfmail(String folderName) {
        return folderListModels.get(folderName).toArray(new Mail[folderListModels.get(folderName).size()]);
    }

    public void addToListOfmail(String folderName, Message message) {
        folderListModels.get(folderName).add(message);
    }

    public void removeToListOfmail(String folderName, Message message) {
        folderListModels.get(folderName).remove(message);
    }

    public void addToListOfmail(String folderName, List<Message> listOfmail) {
        folderListModels.get(folderName).addAll(listOfmail);
    }

    public void addToListOfmail(String folderName, Message[] listOfmail) {
        folderListModels.get(folderName).addAll(Arrays.asList(listOfmail));
    }

    public void removeToListOfmail(String folderName, List<Message> listOfmail) {
        folderListModels.get(folderName).removeAll(listOfmail);
    }

    public void removeToListOfmail(String folderName, Message[] listOfmail) {
        folderListModels.get(folderName).removeAll(Arrays.asList(listOfmail));
    }

    public List<String> getFolderNames() {
        return this.folderNames;
    }

    public void addObservableTo(String observableName, LinkedHashSetPriorityQueueObserver obs) {
        if (folderNames.contains(observableName) || ALL.equals(observableName)) {
            folderListModels.get(observableName).addObserver(obs);
        } else {
            throw new IllegalArgumentException("Ask for unknown observable: " + observableName);
        }
    }

    public ObservableLinkedHashSetPriorityQueue<Message> getListModelFor(String folderName) {
        return this.folderListModels.get(folderName);
    }

    @Override
    public void messagesAdded(MessageCountEvent event) {
        IMAPFolder f = (IMAPFolder) event.getSource();

        Message[] messages = event.getMessages();
        User usr = CookieSwipeApplication.getApplication().getUser();
        for(Message message : messages) {
            try {
                boolean addToMail = true;
                for (Address address : message.getFrom()) {
                    if (usr.getBlackList().contains(address.toString())) {
                        addToMail = false;
                    }
                }
                if (addToMail) {
                    addToListOfmail(f.getName(), message);
                } else {
                    removeToListOfmail(f.getName(), message);
                }
            } catch (MessagingException ex) {
                Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void messagesRemoved(MessageCountEvent event) {
        IMAPFolder f = (IMAPFolder) event.getSource();
        removeToListOfmail(f.getName(), event.getMessages());
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

    // equals & hashcode
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.address);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MailAccount other = (MailAccount) obj;
        if (this.id != other.id) {
            return false;
        }
        return Objects.equals(this.address, other.address);
    }

    @Override
    public String toString() {
        return CSName;
    }

}
