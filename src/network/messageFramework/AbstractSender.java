/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mickx
 * @param <T>
 */
public abstract class AbstractSender<T> {
    
    private final String id;
    private static Logger LOGGER;
    
    private final ConcurrentLinkedQueue<FrameworkMessage<?>> messages;
    
    public AbstractSender() { //Attention id doit etre unique
    	this.id = this.getClass().getName() + hashCode();
    	Postman.registerSender(this);
        if(LOGGER == null) {
            LOGGER = Logger.getLogger(this.getClass().getName());
        }
        messages = new ConcurrentLinkedQueue<>();
    }
    
    public final String getSenderId() {
        return id;
    }
    
    public abstract void onMessageReceived(Future<T> receivedMessage);
    
    public void onAllMessagesReceived() {
    	
    }
    
    /**
     *
     * @param receivedMessage
     * @param frameworkMessage 
     */
    final void onGenericMessageReceived(Future<?> receivedMessage, FrameworkMessage<?> frameworkMessage) {
        try {
            @SuppressWarnings("unchecked")
			Future<T> specifiedMessage = (Future<T>) receivedMessage;
            messages.remove(frameworkMessage);
            onMessageReceived(specifiedMessage);
            if(messages.size() == 0) {
            	onAllMessagesReceived();
            }
        }
        catch(ClassCastException e) {
            LOGGER.log(Level.SEVERE, "Bad message type received", e);
        }
    }
    
    public final void sendMessage(FrameworkMessage<?> sentMessage) {
        sentMessage.setSenderId(getSenderId());
        messages.add(sentMessage);
        Postman.sendMessage(sentMessage);
    }
    
    public final void sendMessages(List<FrameworkMessage<?>> sentMessages) {
    	messages.addAll(sentMessages);
    	for(FrameworkMessage<?> message : sentMessages) {
            message.setSenderId(getSenderId());
            Postman.sendMessage(message);
    	}
    };
}
