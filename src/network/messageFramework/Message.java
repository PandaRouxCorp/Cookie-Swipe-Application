/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 *
 * @author mickx
 */
public abstract class Message<T> implements Callable<T>, Serializable {

    private String senderId;
    
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    
    public String getSenderId() {
        return this.senderId;
    }

    public boolean shouldBeSavedIfNotExecuted() {
        return false;
    }
}
