/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

/**
 *
 * @author mickx
 */
public class Postman {
    
    private static Postman INSTANCE;
    private final Map<Integer, AbstractSender<?>> senders;
    
    private Postman() {
        senders = new HashMap<>();
    }
    
    private void relayMessage(Message<?> message) {
        DeliverySystem.launch(message);
    }
    
    private void relayResponse(int senderID, Future<?> response) {
        senders.get(senderID).onGenericMessageReceived(response);
    }
    
    private void registerSender(int id, AbstractSender sender) {
        senders.put(id, sender);
    }
    
    private void unregisterSender(int id) {
        senders.remove(id);
    }
    
    private boolean isSenderRegistered(int id) {
        return senders.containsKey(id);
    }
    
    public static void registerSender(AbstractSender sender) {
        INSTANCE.registerSender(sender.getSenderId(), sender);
    }
    
    public static void unregisterSender(AbstractSender sender) {
        INSTANCE.unregisterSender(sender.getSenderId());
    }
    
    public static boolean isSenderRegistered(AbstractSender sender) {
        return INSTANCE.isSenderRegistered(sender.getSenderId());
    }
    
    public static void sendMessage(Message<?> message) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.relayMessage(message);
    }

    static void sendResponse(int senderID, Future<?> response) {
        if(INSTANCE == null) {
            throw new UnsupportedOperationException("Postman instance null");
        }
        INSTANCE.relayResponse(senderID, response);
    }
}
