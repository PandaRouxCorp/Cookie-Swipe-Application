/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import model.Mail;

/**
 *
 * @author mickx
 */
public class TestMessage extends Message<Object> {
    int messageDuration;
    Object m;

    public TestMessage(int messageDuration) {
        this.messageDuration = messageDuration;
    }
    
    public TestMessage(int messageDuration, Mail m) {
        this.messageDuration = messageDuration;
        this.m = m;
    }

    @Override
    public Object call() throws Exception {
        Thread.sleep(messageDuration);
        return m;
    }
    
    @Override
    public boolean shouldBeSavedIfNotExecuted() {
        return true;
    }
}
