/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.io.Serializable;

/**
 * Cette classe est un sender serialisable. Ceux-ci doivent etre des singletons.
 * Son etat est sauvegarde lors de l'arret du systeme ainsi ses messages.
 * 
 * Voir utilisation dans Postman.serialize(List<FrameworkMessage<?>>) et Postman.deserialize(List<FrameworkMessage<?>>)
 * 
 * @author mickx
 */
public abstract class AbstractSerializableSender<T> extends AbstractSender<T> implements Serializable {
   
	/**
	 * Cette methode est destine a etre override
	 * Elle est execute juste avant la serialisation du sender
	 */
    public void beforeSerialisation() {
        
    }
    
    /**
	 * Cette methode est destine a etre override
	 * Elle est execute juste avant la deserialisation du sender
	 */
    public void afterDeserialisation() {
        
    }
    
    /**
     * Cette methode permet d'obtenir le singleton du sender a partir du sender deserialise
     * @return
     */
    public abstract AbstractSerializableSender getSingletonSender();
    
    /**
     * Cette methode permet de recuperer les messages qui n'ont pas 
     * ete execute avant l'arret du system
     * @return
     */
    public abstract Object getPendingAction();
    
    /**
     * Cette methode permet de remettre les taches 
     * non executee dans le sender apres deserialisation
     * @param pendingActions
     */
    public abstract void setPendingAction(Object pendingActions);
}
