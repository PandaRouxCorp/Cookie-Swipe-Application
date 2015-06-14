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
	 * @param CSName
	 *            nom donne a la botie courriel
	 * @param address
	 *            adresse courriel de la boite
	 * @param password
	 *            mot de passe pour acceder a la boite courriel
	 * @param color
	 *            couleur donne a la boite courriel pour l'affichage des
	 *            courriels
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
	 * @param id
	 *            identifiant unique de la boite mail
	 * @param CSName
	 *            nom donne a la boite courriel
	 * @param address
	 *            adresse courriel de la boite
	 * @param password
	 *            mot de passe pour acceder a la boite mail
	 * @param color
	 *            couleur donne a la boite courriel pour l'affichage des
	 *            courriels
	 * @param domain
	 *            domaine de l'adresse courriel
	 * @param mailSignature
	 *            signature a mettre a la fin d'un courriel
	 * @param lastSynch
	 *            date de la derniere synchronisation reussi de la boite
	 *            courriel
	 * @param listOfMail
	 *            Liste des courriels de la boite mail
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
	 * @param data
	 *            table de hash contenant les donnees du compte courriel
	 * @return Si la mise a jours des donnees est correct
	 */
	public boolean updateData(HashMap<String, Object> data) {
		return false;
	}

	/**
	 * r�cupere la liste de mails
	 * 
	 * @throws Exception
	 */
	public void getMessages() throws Exception {

            // Definir les parametres de connexion
            Session session = Session.getDefaultInstance(
                new Properties(),
                new javax.mail.Authenticator() {
                    public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(address, password);
                    }
            });
            
            Store store = session.getStore("pop3");
            
            System.out.println(domain.getServerIn() + " " +  address + " " + password);

            
            
            // Ouvrir la connexion
            store.connect(domain.getServerIn(), address, password);

            System.out.println("Vous ete connecte a " + domain.getServerIn());

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
                    for (Address f : message.getFrom())
                            from += f.toString() + "; ";
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
	
	private Session getSession() {

		Properties properties = new Properties();

		properties.setProperty("mail.transport.protocol", "smtp");
		 properties.setProperty("mail.smtp.host", domain.getServerOut());
		 properties.setProperty("mail.smtp.user", domain.getAddress());
		 properties.setProperty("mail.smtp.port", domain.getPortOut());
//		 properties.setProperty("mail.from", domain.getServerIn());

		return Session.getInstance(properties);
	}

	public MimeMessage writeMail(String subject, String text, String to, String copyDest) throws MessagingException {
		MimeMessage message = new MimeMessage(getSession());
		message.setText(text);
		message.setSubject(subject);
		message.setFrom(address);
//		message.addRecipients(javax.mail.Message.RecipientType.TO, to);
//		if(copyDest != null && !copyDest.isEmpty())
//			message.addRecipients(javax.mail.Message.RecipientType.CC, copyDest);
		return message;
	}

	public MimeMessage writeMail(Mail mail) throws MessagingException {
		return writeMail(mail.getSubject(), mail.getBody(), mail.getTo(), mail.getCopyTo());
	}

	/**
	 * Sert a creer un nouveau courriel
	 *
	 * @return courriel cree
	 */
	public Mail createNewMail() {
		return null;
	}

	/**
	 * Sert a envoyer un courriel, delegue l'envoi a un domaine
	 *
	 * @param mail
	 *            courriel a envoyer
	 * @return Si l'envoi c'est bien passe
	 */
	public boolean sendMail(Mail mail) {
		Transport transport = null;
		try {
			Session session = getSession();
			transport = session.getTransport("smtp");
			transport.connect(address, password);
			transport.sendMessage(writeMail(mail), new Address[] {
					new InternetAddress(mail.getTo()),
					new InternetAddress(mail.getCopyTo()) });
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (transport != null) {
					transport.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * Sert a suppriemr un courriel de la boite courriel
	 *
	 * @param mail
	 *            courriel a supprimer
	 * @return Si la suppresion c'est bien passe
	 */
	public boolean deleteMail(Mail mail) {
		return false;
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
		Domain domain = null;

		switch (mailDomain) {
		case "yahoo.fr":
			domain = new Domain("Yahoo", mailDomain, "pop.mail.yahoo.fr",
					"995", "smtp.mail.yahoo.fr", "465", 1);
			break;
		case "hotmail.com":
		case "hotmail.fr":
		case "live.com":
		case "live.fr":
		case "msn.com":
		case "outlook.com":
			domain = new Domain("Microsoft", mailDomain, "pop3.live.com",
					"995", "smtp.live.com", "587", 2);
			break;
		case "orange.fr":
		case "wanadoo.fr":
			domain = new Domain("Orange", mailDomain, "", "", "", "", 3);
			break;
		case "gmail.com":
			domain = new Domain("Google", mailDomain, "imap.gmail.com", "993",
					"smtp.gmail.com", "465", 4);
			break;
		default:
			return CodeError.DOMAIN_NOT_SUPPORTED;

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
