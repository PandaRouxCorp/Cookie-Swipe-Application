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
import network.messageFramework.AbstractSerializableSender;
import network.messageFramework.Postman;


/**
 *
 * @author mickx
 */
public class MailSender extends AbstractSerializableSender<Mail> {
    
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
            System.out.println("Acknowlegment recieved");
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
    
    @Override
    public void beforeSerialisation() {
        System.out.println("I'm serialized");
    }

    @Override
    public void afterDeserialisation() {
        System.out.println("I'm deserialized");
    }
    
    @Override
    public Object getPendingAction() {
        return mails;
    }
    
    @Override
    public void setPendingAction(Object pendingActions) {
        mails.addAll((ConcurrentLinkedQueue<Mail>)pendingActions);
    }


    @Override
    public AbstractSerializableSender getSingletonSender() {
        if(INSTANCE == null) {
            INSTANCE = new MailSender();
        }
        return INSTANCE;
    }
    
    public static void send(Mail m) {
        if(INSTANCE == null) {
            INSTANCE = new MailSender();
        }
        INSTANCE.add(m);
    }
}
