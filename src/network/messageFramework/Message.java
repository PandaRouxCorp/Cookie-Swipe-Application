/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.util.concurrent.Callable;

/**
 *
 * @author mickx
 */
public abstract class Message<T> implements Callable<T>{

    private final String senderId;
    
    public Message(String senderId) {
        this.senderId = senderId;
    }
    
    public String getSender() {
        return this.senderId;
    }
}
