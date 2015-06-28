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

import module.ihm.CustomJListModel;
import module.ihm.MainFrameInitializer;
import cookie.swipe.application.CookieSwipeApplication;
import cookie.swipe.application.utils.LinkedHashSetPriorityQueueObserver;
import cookie.swipe.application.utils.ObservableLinkedHashSetPriorityQueue;
import errorMessage.CodeError;

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
    private Mail currentMail;
    private HashMap<String,ObservableLinkedHashSetPriorityQueue<Message>> folderListModels;
    private List<String> folderNames;

    // Constructeur
    /**
     * Constructeur par defaut
     */
    public MailAccount() {
        domain = new Domain();
        this.folderNames = new ArrayList<>();
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
    	
    	ObservableLinkedHashSetPriorityQueue<Message> observableList = 
    			new ObservableLinkedHashSetPriorityQueue<>(new MailComparator());
    	
    	folderListModels.put(folder, observableList);
    	
    	HashMap<String, HashMap<String, CustomJListModel>> models = 
				(HashMap<String, HashMap<String, CustomJListModel>>) 
				CookieSwipeApplication.getApplication().getParam("jListMailModels");
    	
    	HashMap<String, CustomJListModel> jListModels = models.get(getCSName());
    	
    	if(jListModels == null) {
    		jListModels = new HashMap<>();
    		models.put(getCSName(), jListModels);
    	}
    	
    	jListModels.put(folder, new CustomJListModel(observableList));
    	
    	MainFrameInitializer frameInitializer = new MainFrameInitializer();
    	frameInitializer.addFolderInTree(this,folder);
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

    private Properties getProperties() {
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

        // version pour test gmail avec le compte panda.roux.corp@gmail.com
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.user", "panda.roux.corp@gmail.com");
//        props.put("mail.smtp.port", "465");
//        props.put("mail.from", "pop.gmail.com");

        return props;
    }

    /**
     * Sert a creer un nouveau courriel
     *
     * @return courriel cree
     */
    public Mail createNewMail() {
        currentMail = new Mail();
        return currentMail;
    }

    public void addDestinataire(String destinataire) {
        String to = currentMail.getSubject();
        if (to == null || to.isEmpty()) {
            to = destinataire;
        } else {
            to += "; " + destinataire;
        }
        currentMail.setTo(to);
    }

    public void addSubject(String subject) {
        currentMail.setSubject(subject);
    }

    /**
     * Sert a envoyer un courriel, delegue l'envoi a un domaine
     *
     * @return Si l'envoi c'est bien passe
     */
    public boolean sendMail() {
        try {
            Session session = Session.getDefaultInstance(getProperties(),
                new javax.mail.Authenticator() {
                    @Override
                    public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(address, password);
//                        return new javax.mail.PasswordAuthentication("panda.roux.corp@gmail.com", "Panda123456789");
                    }
                }
            );
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(address));
//            message.setFrom(new InternetAddress("panda.roux.corp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(currentMail.getTo()));
//                InternetAddress.parse("yehouda_arnauve@hotmail.com"));
            message.setSubject(currentMail.getSubject());
            message.setText(currentMail.getBody());
//            message.setSubject("Testing Subject");
//            message.setText("Dear Mail Crawler,"
//                + "\n\n No spam to my email, please!");
            
            
            System.out.println("before send");
            
            Transport.send(message);
            
            System.out.println("after send");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setMail(Mail mail) {
        currentMail = mail;
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

    public void addAttachement(File file) {
        currentMail.addAttachement(file);
    }

    public void addAttachement(String filename) {
        currentMail.addAttachement(new File(filename));
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

    public void addToListOfmail(String folderName, List<Message> listOfmail) {
    	folderListModels.get(folderName).addAll(listOfmail);
    }
    
    public void addToListOfmail(String folderName, Message[] listOfmail) {
    	folderListModels.get(folderName).addAll(Arrays.asList(listOfmail));
    }
    
    public List<String> getFolderNames() {
    	return this.folderNames;
    }

    //response & forward mail
    public Mail response(Mail mail) {
        Mail resp = new Mail();
        resp.setBody(mailTranfer(mail));
        resp.setTo(mail.getFrom());
        resp.setFrom(mail.getTo());
        resp.setSubject("FW : " + mail.getSubject());
        return resp;
    }

    public Mail forward(Mail mail) {
        Mail resp = new Mail();
        resp.setAttachement(mail.getAttachement());
        resp.setBody(mailTranfer(mail));
        resp.setSubject("FW : " + mail.getSubject());
        return resp;
    }

    private String mailTranfer(Mail mail) {
        return "------------------------\n"
                + "From : " + mail.getFrom() + "\n"
                + "To : " + mail.getTo() + "\n"
                + "Date : " + mail.getDate() + "\n\n"
                + mail.getBody()
                + "";
    }
    
    public void addObservableTo(String observableName, LinkedHashSetPriorityQueueObserver obs) {
    	if(folderNames.contains(observableName) || ALL.equals(observableName)) {
    		folderListModels.get(observableName).addObserver(obs);
    	}
    	else {
    		throw new IllegalArgumentException("Ask for unknown observable: " + observableName);
    	}
    }
    
    public ObservableLinkedHashSetPriorityQueue<Message> getListModelFor(String folderName) {
		return this.folderListModels.get(folderName);
	}
    
    @Override
	public void messagesAdded(MessageCountEvent event) {
    	event.getSource();
    	//addToListOfmail(event.getMessages());
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
