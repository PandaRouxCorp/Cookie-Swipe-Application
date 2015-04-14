/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.io.Serializable;

/**
 *
 * @author mickx
 */
public interface SerializableSender extends Serializable {
    public void beforeSerialisation();
    public void afterDeserialisation();
    public AbstractSender getSingletonSender();
    public Object getPendingAction(); 
    public void setPendingAction(Object pendingActions);
}
