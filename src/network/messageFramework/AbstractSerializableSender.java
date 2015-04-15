/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

/**
 *
 * @author mickx
 */
public abstract class AbstractSerializableSender<T> extends AbstractSender<T> {
    
    public void beforeSerialisation() {
        
    }
    
    public void afterDeserialisation() {
        
    }
    
    public abstract AbstractSerializableSender getSingletonSender();
    public abstract Object getPendingAction();
    public abstract void setPendingAction(Object pendingActions);
}
