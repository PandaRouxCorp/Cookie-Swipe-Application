/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.io.Serializable;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mickx
 * @param <T>
 */
public abstract class AbstractSender<T> implements Serializable {
    
    private final int id;
    private static Logger LOGGER;
    
    public AbstractSender() { //Attention id doit etre unique
        this.id = hashCode();
        if(LOGGER == null) {
            LOGGER = Logger.getLogger(AbstractSender.class.getName());
        }
    }
    
    public int getSenderId() {
        return id;
    }
    
    public abstract void onMessageReceived(Future<T> receivedMessage);
    
    /**
     *
     * @param receivedMessage
     */
    public void onGenericMessageReceived(Future<?> receivedMessage) {
        try {
            Future<T> specifiedMessage = (Future<T>) receivedMessage;
            onMessageReceived(specifiedMessage);
        }
        catch(ClassCastException e) {
            LOGGER.log(Level.SEVERE, "Bad message type received", e);
        }
    }
    
    public void sendMessage(Message sentMessage) {
        Postman.sendMessage(sentMessage);
    };
}
