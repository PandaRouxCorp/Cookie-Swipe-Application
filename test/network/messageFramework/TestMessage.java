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
public class TestMessage extends Message<Integer> {
    int messageDuration;

    public TestMessage(int messageDuration) {
        this.messageDuration = messageDuration;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(messageDuration);
        return getSender();
    }
    
    @Override
    public boolean shouldBeSavedIfNotExecuted() {
        return true;
    }
}
