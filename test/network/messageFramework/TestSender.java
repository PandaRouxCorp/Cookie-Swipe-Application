/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import model.Mail;
import network.mail.MailSender;

/**
 *
 * @author mickx
 */
public class TestSender extends AbstractSerializableSender {

    private static TestSender INSTANCE;

    private final ConcurrentLinkedQueue<Object> objects;
    
    private TestSender() {
        objects = new ConcurrentLinkedQueue<>();
        Postman.registerSender(this);
    }
    
    @Override
    public void onMessageReceived(Future receivedMessage) {
        System.out.println("Receive acknowledgement");
    }

    @Override
    public AbstractSerializableSender getSingletonSender() {
        if(INSTANCE == null) {
            INSTANCE = new TestSender();
        }
        return INSTANCE;
    }

    @Override
    public Object getPendingAction() {
        return objects;
    }

    @Override
    public void setPendingAction(Object pendingActions) {
        this.objects.addAll((ConcurrentLinkedQueue<Object>)pendingActions);
    }
    
    public static void send(Mail m) {
        if(INSTANCE == null) {
            INSTANCE = new TestSender();
        }
        INSTANCE.add(m);
    }
    
    public static TestSender getSender() {
        if(INSTANCE == null) {
            INSTANCE = new TestSender();
        }
        return INSTANCE;
    }

    private void add(Mail m) {
        this.objects.add(m);
        sendMessage(new TestMessage(100, m));
    }
}
