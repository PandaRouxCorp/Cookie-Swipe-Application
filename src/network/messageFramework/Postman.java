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
 * Cette classe permet de faire le lien entre le sender 
 * qui envoie des messages au systeme de taches asynchrones
 * et le resultat de l'execution de la tache 
 * 
 * @author mickx
 */
public class Postman {
    
    public static final String COOKIE_SWIPE_DIR = System.getProperty("user.dir") + "/CSstate.tmp";
    private static Postman INSTANCE;
    private final Map<String, AbstractSender<?>> senders;
    private final Logger LOGGER;
    
    private Postman() {
        LOGGER = Logger.getLogger(DeliverySystem.class.getName());
        senders = new HashMap<>();
    }
    
    /**
     * Cette methode transmet le message au systeme de taches asynchrones
     * @param message
     */
    private void relayMessage(FrameworkMessage<?> message) {
        DeliverySystem.launch(message);
    }
    
    /**
     * Cette methode renvoyer au sender sa tache executee 
     * @param senderID
     * @param response
     * @param frameworkMessage
     */
    private void relayResponse(String senderID, Future<?> response, FrameworkMessage<?> frameworkMessage) {
        AbstractSender s = senders.get(senderID);
        if(s != null) {
            s.onGenericMessageReceived(response, frameworkMessage);
        }
        else {
            LOGGER.log(Level.WARNING, "Unknow sender {0}. FrameworkMessage response not relay", senderID);
        }
    }
    
    /**
     * Cette methode permet d'enregistrer le sender
     * c'est à dire de lier un id a un sender
     * 
     * @param id
     * @param sender
     */
    private void registerSender(String id, AbstractSender sender) {
        senders.put(id, sender);
    }
    
    /**
     * Cette methode permet d'effacer le sender 
     * identifie par l'id passe en parametre
     * 
     * @param id
     */
    private void unregisterSender(String id) {
        senders.remove(id);
    }
    
    /**
     * Cette methode permet de savoir si un sender a ete enregistre
     * @param id
     * @return
     */
    private boolean isSenderRegistered(String id) {
        return senders.containsKey(id);
    }
    
    /**
     * Cette methode permet d'enregistrer un sender
     * @param sender
     */
    
    public static void registerSender(AbstractSender sender) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.registerSender(sender.getSenderId(), sender);
    }
    
    /**
     * Cette methode permet d'effacer le sender
     * Il n'aura plus de retour sur les messages qu'il enverra 
     * 
     * @param sender
     */
    public static void unregisterSender(AbstractSender sender) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.unregisterSender(sender.getSenderId());
    }
    
    /**
     * Cette methode permet de savoir si un sender a ete enregistre
     * @param sender
     * @return
     */
    public static boolean isSenderRegistered(AbstractSender sender) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        return INSTANCE.isSenderRegistered(sender.getSenderId());
    }
    
    /**
     * Cette methode permet de lancer une tache asynchrone
     * @param message
     */
    public static void sendMessage(FrameworkMessage<?> message) {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.relayMessage(message);
    }

    /**
     * Cette methode permet de renvoyer au sender sa tache executee
     * @param senderID
     * @param response
     * @param frameworkMessage
     */
    static void sendResponse(String senderID, Future<?> response, FrameworkMessage<?> frameworkMessage) {
        if(INSTANCE != null) {
            INSTANCE.relayResponse(senderID, response, frameworkMessage);
        }
        else {
            throw new UnsupportedOperationException("Postman instance null");
        }
    }
    
    /**
     * Cette methode permet de serialiser les sender et les messages en cours d'execution  
     * @param frameworkMessages
     */
    static void serializeMessages(List<FrameworkMessage<?>> frameworkMessages) {
        if(INSTANCE != null) {
            INSTANCE.serialize(frameworkMessages);
        }
        else {
            throw new UnsupportedOperationException("Postman instance null");
        }
    }
    
    /**
     * Cette methode permet de recupérer l'état des 
     * messages et des sender au moment de l'arret du systeme
     */
    static void retreiveSavedMessages() {
        if(INSTANCE == null) {
            INSTANCE = new Postman();
        }
        INSTANCE.deserializedMessages();
    }

    /**
     * Cette methode permet de serialiser les messages qui n'ont pas ete envoyes
     *
     * @param frameworkMessages
     */
    private void serialize(List<FrameworkMessage<?>> frameworkMessages) {
        ObjectOutputStream oos = null;
        try {
            File file = new File(COOKIE_SWIPE_DIR);
            if(file.exists()) {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);
            Map<FrameworkMessage<?>,AbstractSender<?>> mapToSerialized = new HashMap<>();
//            frameworkMessages.stream().forEach((m) -> {
//                AbstractSender<?> sender = senders.get(m.getSenderId());
//                if(sender != null && m.shouldBeSavedIfNotExecuted() && sender instanceof AbstractSerializableSender) {
//                    ((AbstractSerializableSender<?>)sender).beforeSerialisation();
//                    mapToSerialized.put(m,sender);
//                }
//                else {
//                    Logger.getLogger(DeliverySystem.class.getName())
//                            .log(Level.SEVERE,
//                                    "Serialisation error: sender have been unregistered. FrameworkMessage is lost");
//                }
//            });
            
            for(FrameworkMessage m : frameworkMessages) {
               AbstractSender sender = senders.get(m.getSenderId());
                if(sender != null && m.shouldBeSavedIfNotExecuted() && sender instanceof AbstractSerializableSender) {
                    ((AbstractSerializableSender)sender).beforeSerialisation();
                    mapToSerialized.put(m,sender);
                }
                else {
                    Logger.getLogger(DeliverySystem.class.getName())
                            .log(Level.SEVERE,
                                    "Serialisation error: sender have been unregistered. FrameworkMessage is lost");
                } 
            }
            
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
    
    /**
     * Cette methode permet de deserialiser les messages qui n'ont pas ete envoyes
     */
    private void deserializedMessages() {
        File file = new File(COOKIE_SWIPE_DIR);
        ObjectInputStream ois = null;
        
        if(!file.exists()) return;
        
        try {
            FileInputStream fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            Map<FrameworkMessage,AbstractSender> map = (Map<FrameworkMessage,AbstractSender>) ois.readObject();

    //            map.keySet().stream().forEach((m) -> {
//                AbstractSerializableSender sender = (AbstractSerializableSender) map.get(m);
//                Object pendingActions = sender.getPendingAction();
//                sender = sender.getSingletonSender();
//                sender.setPendingAction(pendingActions);
//                sender.afterDeserialisation();
//                if(!isSenderRegistered(sender.getSenderId())) {
//                    registerSender(sender);
//                }
//                m.setSenderId(sender.getSenderId());
//                sendMessage(m);
//            });
            
            for(FrameworkMessage m : map.keySet()) {
                AbstractSerializableSender sender = (AbstractSerializableSender) map.get(m);
                Object pendingActions = sender.getPendingAction();
                sender = sender.getSingletonSender();
                sender.setPendingAction(pendingActions);
                sender.afterDeserialisation();
                if(!isSenderRegistered(sender.getSenderId())) {
                    registerSender(sender);
                }
                m.setSenderId(sender.getSenderId());
                sendMessage(m);
            }
            
            file.delete();
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
        }
    }
}
