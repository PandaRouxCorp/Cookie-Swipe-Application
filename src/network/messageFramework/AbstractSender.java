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
 * Cette classe represente un sender
 * C'est en fait un objet qui est capable de demander l'execution de taches asynchrones
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
    
    /**
     * Cette methode permet d'avoir l'id du sender
     * @return
     */
    public final String getSenderId() {
        return id;
    }
    
    /**
     * Cette methode correspond a l'action qu'il faut faire en réponse à un message
     * C'est en fait une callback
     * 
     * @param receivedMessage
     */
    public abstract void onMessageReceived(Future<T> receivedMessage);
    
    
    /**
     * Cette methode est destine override
     * Elle est appele des que la queue des messages qui ont ete envoye et dont on attend le retour est vide
     */
    public void onAllMessagesReceived() {
    	
    }
    
    /**
     * Cette methode est appele à la reception d'un message retour
     * Elle permet de caster les objets dans le bon type
     * Si toutes les taches ont ete executee, elle le notifie egalement
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
    
    /**
     * Methode qui permet d'envoyer une tache a execute
     * @param sentMessage
     */
    public final void sendMessage(FrameworkMessage<?> sentMessage) {
        sentMessage.setSenderId(getSenderId());
        messages.add(sentMessage);
        Postman.sendMessage(sentMessage);
    }
    
    /**
     * Methode qui envoie les messages a executes
     * @param sentMessages
     */
    public final void sendMessages(List<FrameworkMessage<?>> sentMessages) {
    	messages.addAll(sentMessages);
    	for(FrameworkMessage<?> message : sentMessages) {
            message.setSenderId(getSenderId());
            Postman.sendMessage(message);
    	}
    };
}
