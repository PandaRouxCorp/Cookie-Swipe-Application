/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mickx
 */
public class Postman {
    
    public static final String COOKIE_SWIPE_DIR = System.getProperty("user.dir") + "/CSstate.tmp";
    private static Postman INSTANCE;
    private final Map<Integer, AbstractSender<?>> senders;
    private final Logger LOGGER;
    
    private Postman() {
        LOGGER = Logger.getLogger(DeliverySystem.class.getName());
        senders = new HashMap<>();
    }
    
    private void relayMessage(Message<?> message) {
        if(message == null) DeliverySystem.launch();
        else
            DeliverySystem.launch(message);
    }
    
    private void relayResponse(int senderID, Future<?> response) {
        AbstractSender s = senders.get(senderID);
        if(s != null) {
            s.onGenericMessageReceived(response);
        }
        else {
            LOGGER.log(Level.WARNING, "Unknow sender {0}. Message response not relay", senderID);
        }
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
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.registerSender(sender.getSenderId(), sender);
    }
    
    public static void unregisterSender(AbstractSender sender) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.unregisterSender(sender.getSenderId());
    }
    
    public static boolean isSenderRegistered(AbstractSender sender) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        return INSTANCE.isSenderRegistered(sender.getSenderId());
    }
    
    public static void sendMessage(Message<?> message) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.relayMessage(message);
    }

    static void sendResponse(int senderID, Future<?> response) {
        if(INSTANCE != null) {
            INSTANCE.relayResponse(senderID, response);
        }
        else {
            throw new UnsupportedOperationException("Postman instance null");
        }
    }
    
    static void serializeMessages(List<Message> messages) {
        if(INSTANCE != null) {
            INSTANCE.serialize(messages);
        }
        else {
            throw new UnsupportedOperationException("Postman instance null");
        }
    }
    
    static void retreiveSavedMessages() {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.deserializedMessages();
    }

    private void serialize(List<Message> messages) {
        ObjectOutputStream oos = null;
        try {
            File file = new File(COOKIE_SWIPE_DIR);
            if(file.exists()) {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);
            Map<Message,AbstractSender> mapToSerialized = new HashMap<>();
            messages.stream().forEach((m) -> {
                AbstractSender sender = senders.get(m.getSender());
                if(sender != null && m.shouldBeSavedIfNotExecuted() && sender instanceof AbstractSerializableSender) {
                    ((AbstractSerializableSender)sender).beforeSerialisation();
                    mapToSerialized.put(m,sender);
                }
                else {
                    Logger.getLogger(DeliverySystem.class.getName())
                            .log(Level.SEVERE,
                                    "Serialisation error: sender have been unregistered. Message is lost");
                }
            });
            oos.writeObject(mapToSerialized);
        } catch (IOException ex) {
            Logger.getLogger(DeliverySystem.class.getName()).log(Level.SEVERE, "Serialisation error", ex);
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Postman.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void deserializedMessages() {
        File file = new File(COOKIE_SWIPE_DIR);
        ObjectInputStream ois = null;
        try {
            if(file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                Map<Message,AbstractSender> map = (Map<Message,AbstractSender>) ois.readObject();
                map.keySet().stream().forEach((m) -> {
                    AbstractSerializableSender sender = (AbstractSerializableSender) map.get(m);
                    Object pendingActions = sender.getPendingAction();
                    sender = sender.getSingletonSender();
                    sender.setPendingAction(pendingActions);
                    sender.afterDeserialisation();
                    if(!isSenderRegistered(m.getSender())) {
                        registerSender(map.get(m));
                    }
                    sendMessage(m);
                });
            }
        } catch (IOException
                |ClassNotFoundException
                |ClassCastException ex) {
            Logger.getLogger(DeliverySystem.class.getName()).log(Level.SEVERE, "Deserialisation error", ex);
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(Postman.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            file.delete();
        }
    }
}
