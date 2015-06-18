/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import errorMessage.CodeError;
import java.io.File;
import javax.mail.Message;

/**
 *
 * @author Mary
 */
public class MailAccount {

    // Variable membre
    private int id;
    private String address, CSName, password, color, mailSignature;
    private Domain domain;
    private Date lastSynch;
    private ArrayList<Mail> listOfmail;
    private Mail currentMail;

    // Constructeur
    /**
     * Constructeur par defaut
     */
    public MailAccount() {
        domain = new Domain();
        listOfmail = new ArrayList<>();
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
        listOfmail = new ArrayList<>();
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
            ArrayList<Mail> listOfMail) {
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
        this.listOfmail = listOfMail;
    }

    // Fonction membre public
    /**
     * Renvoie les toutes donnees du compte courriel
     *
     * @return table de hash contenant toutes les donnees du compte courriel
     */
    public HashMap<String, Object> getData() {

        return null;
    }

    /**
     * Modifie les donnees du compte courrie
     *
     * @param data table de hash contenant les donnees du compte courriel
     * @return Si la mise a jours des donnees est correct
     */
    public boolean updateData(HashMap<String, Object> data) {
        return false;
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
        session.setDebug(true);
        
        Store store = session.getStore(domain.getStoreProtocol());
        
        // Ouvrir la connexion
        store.connect(domain.getServerIn(), address, new Encryption().decrypt(password));
        
        return store;
    }
    

    /**
     * r�cupere la liste de mails
     *
     * @throws Exception
     */
    public void getMessages() throws Exception {

        Store store = getClientConnection();

        // Ouverture de la boite de reception
        Folder inbox = store.getFolder("INBOX");
        if (inbox == null) {
            System.out.println("Boite de Reception introuvale");
        }
        inbox.open(Folder.READ_ONLY);
        int count = inbox.getMessageCount();

        // recuperation de tous les mails et les mettres dans la liste
        for (int i = 0; i < count; i++) {
            javax.mail.Message message = inbox.getMessage(i);
            Mail mail = new Mail();
            mail.setBody(message.getDescription());
            mail.setSubject(message.getSubject());
            System.out.println(mail.getSubject());
            mail.setDate((Date) message.getReceivedDate());
            String from = "";
            for (Address f : message.getFrom()) {
                from += f.toString() + "; ";
            }
            mail.setFrom(from);
            // verifier doublons avant ou clear la list.
            listOfmail.add(mail);
        }
        store.close();

    }

    public void readMessage() throws Exception {
        // Definir les parametres de connexion
        Session session = Session.getDefaultInstance(new Properties(),
                new javax.mail.Authenticator() {
                    @Override
                    public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(address, password);
                    }
                });
        Store store = session.getStore("pop3");

        // Ouvrir la connexion
        store.connect(domain.getServerIn(), address, password);

        System.out.println("Vous etes connecte a " + domain.getServerIn());

        // Ouverture de la boite de reception
        Folder inbox = store.getFolder("INBOX");
        if (inbox == null) {
            System.out.println("Boite de Reception introuvale");
        }
        inbox.open(Folder.READ_ONLY);

        // Selectionner tous les messages du repertoire ouvert
        javax.mail.Message[] messages = inbox.getMessages();

        // Afficher le nombre de message
        System.out.println("Vous avez: " + messages.length + " message(s)");

        System.out.println("Voici le contenu d'un message:");
        System.out.println();

        // Afficher le contenu d'un message
        if (messages.length >= 1) {
            // messages[1].writeTo(System.out);
        }

        store.close();
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

    public ArrayList<Mail> getListOfmail() {
        return listOfmail;
    }

    public void setListOfmail(ArrayList<Mail> listOfmail) {
        this.listOfmail = listOfmail;
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
