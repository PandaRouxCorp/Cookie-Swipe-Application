/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import dao.DAOUser;
import errorMessage.CodeError;
import interfaces.AbstractIHMAction;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Domain;
import model.Encryption;
import model.MailAccount;
import model.User;
import network.messageFramework.AbstractSender;
import network.messageFramework.FrameworkMessage;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Yehouda
 */
public class ForgottenAction extends AbstractIHMAction {

    private Domain domain;
    private Message currentMessage;
    private String address;
    private String password;
    private String message;
    private String subject;
    
    public ForgottenAction(CookieSwipeFrame csFrame) {
        super(csFrame);
        address = "panda.roux.corp@gmail.com";
        password = "Panda123456789";
        try {
            domain = new Domain("Google", address, "imap.gmail.com", "smtp.gmail.com", "993", "465", "imaps", 4);
        } catch (Exception ex) {
            Logger.getLogger(ForgottenAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean execute(Object... object) {
        String action = (String) object[0];
        int error = CodeError.SUCESS;
        User usr = new User();
        String mail = ((JTextField) hsJcomponent.get("cookieSwipeTextFieldMailAddress")).getText();
        usr.setBackupMail(mail);
        int err = DAOUser.getUserByBackupAddress(usr);
        switch(action) {
            case "login":
                subject = "forgotten login";
                if( err == 0) {
                    message = "votre login est : " + usr.getLoginAdressMail();
                    sendMail(mail);
                } else 
                    error = CodeError.FAILLURE;
                break;
            case "password":
                subject = "forgotten password";
                if( err == 0) {
                    String pwd = "";
                    try {
                        pwd = new Encryption().decrypt(usr.getPassword());
                    } catch (Exception ex) {
                        Logger.getLogger(ForgottenAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(!pwd.isEmpty()) {
                        message = "votre password est : " + pwd;
                        sendMail(mail);
                    }
                } else 
                    error = CodeError.FAILLURE;
                break;
        }

        switch (error) {
            case CodeError.SUCESS:
                JOptionPane.showMessageDialog(null, "Votre login vous à bien été envoyé par mail",
                        "Envoi d'un mail", JOptionPane.INFORMATION_MESSAGE);
                return true;
            case CodeError.CONNEXION_FAIL:
                JOptionPane.showMessageDialog(null, "Problème de connexion \nCode erreur : " + error,
                        "Envoi d'un mail", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                        "Envoi d'un mail", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
    
    /**
     * Sert a envoyer un courriel, delegue l'envoi a un domaine )
     * @param mail
     */
    public void sendMail(final String mail) {
        currentMessage = new MimeMessage(getSession());
        
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
                        case CodeError.CONNEXION_FAIL:
                            JOptionPane.showMessageDialog(null, "Problème de connexion \nCode erreur : " + error,
                                    "Envoi d'un mail", JOptionPane.ERROR_MESSAGE);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                                    "Envoi d'un mail", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(MailAccount.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        
        s.sendMessage(new FrameworkMessage< Boolean>() {
            @Override
            public Boolean call() throws Exception {

                try {
                    currentMessage = new MimeMessage(getSession());
                    currentMessage.setFrom(new InternetAddress(address));
                    currentMessage.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(mail));
                    currentMessage.setSubject(subject);
                    currentMessage.setText(message);
                    Transport.send(currentMessage);
                    return true;
                } catch (MessagingException e) {
                }
                return false;
            }
        });
    }

    public Session getSession() {

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
        
        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        public javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            
                            return new javax.mail.PasswordAuthentication(address, password);
                        }
                    }
            );
        return session;
    }
}
