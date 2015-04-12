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

    private final int senderId;
    
    public Message(int senderId) {
        this.senderId = senderId;
    }
    
    public int getSender() {
        return this.senderId;
    }
}
