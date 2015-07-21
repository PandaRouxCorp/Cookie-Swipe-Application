/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Cette classe est un enrichissement de la classe Callable
 * afin qu'elle soit sérialisable et puisse avoir un identifiant
 * qui correspond a l'objet qui a commandite son execution
 * 
 * L'objet en question doit etendre AbstractSender
 * @author mickx
 */
public abstract class FrameworkMessage<T> implements Callable<T>, Serializable {

	private static final long serialVersionUID = -951943707626400708L;

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
