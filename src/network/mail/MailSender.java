/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.mail;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mail;
import network.messageFramework.AbstractSender;
import network.messageFramework.Message;
import network.messageFramework.Postman;

/**
 *
 * @author mickx
 */
public class MailSender extends AbstractSender<Mail> {
    
    private static MailSender INSTANCE;    
    private final ConcurrentLinkedQueue<Mail> mails;
    
    private MailSender() {
        mails = new ConcurrentLinkedQueue<>();
        Postman.registerSender(this);
    }
    
    @Override
    public void onMessageReceived(Future<Mail> receivedMessage) {
        try {
            Mail mailAcknowledgment = receivedMessage.get();
            if(mails.remove(mailAcknowledgment) == false) {
                 Logger.getLogger(MailSender.class.getName())
                    .log(Level.SEVERE, "Erreur: Accusé de réception pour mail inconnu");
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MailSender.class.getName())
                    .log(Level.SEVERE, "Erreur lors de la récupération de l'accusé de réception", ex);
        }
    }
    
    private void add(Mail m) {
        mails.add(m);
        sendMessage(new MailMessage(m));
    }
    
    public static void send(Mail m) {
        if(INSTANCE == null) {
            INSTANCE = new MailSender();
        }
        INSTANCE.add(m);
    }
}
