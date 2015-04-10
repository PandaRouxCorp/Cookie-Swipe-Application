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
    private final Map<String, AbstractSender<Object>> senders;
    
    private Postman() {
        senders = new HashMap<>();
    }
    
    private void relayMessage(Message<Object> message) {
        RequestLauncher.launch(message);
    }
    
    private void relayResponse(String senderID, Future<Object> response) {
        senders.get(senderID).onMessageReceived(response);
    }
    
    private void registerSender(String id, AbstractSender sender) {
        senders.put(id, sender);
    }
    
    private void unregisterSender(String id) {
        senders.remove(id);
    }
    
    public static void registerSender(AbstractSender sender) {
        INSTANCE.registerSender(sender.getSenderId(), sender);
    }
    
    public static void unregisterSender(AbstractSender sender) {
        INSTANCE.unregisterSender(sender.getSenderId());
    }
    
    public static void sendMessage(Message<Object> message) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.relayMessage(message);
    }

    static void sendResponse(String senderID, Future<Object> response) {
        if(INSTANCE == null) {
            throw new UnsupportedOperationException("Postman instance null");
        }
        INSTANCE.relayResponse(senderID, response);
    }
}
