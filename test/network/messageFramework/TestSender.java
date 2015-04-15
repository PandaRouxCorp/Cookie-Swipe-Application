/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;

/**
 *
 * @author mickx
 */
class TestSender extends AbstractSerializableSender {

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
    
    public static TestSender getSender() {
        if(INSTANCE == null) {
            INSTANCE = new TestSender();
        }
        return INSTANCE;
    }
}
