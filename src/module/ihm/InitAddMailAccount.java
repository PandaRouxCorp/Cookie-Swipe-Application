/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module.ihm;

import controller.Dispatcher;
import interfaces.IActionIHM;
import interfaces.IJFrame;
import java.util.HashMap;
import javax.swing.JButton;

/**
 *
 * @author Lucas
 */
public class InitAddMailAccount implements IActionIHM{
    
    private Dispatcher dispatcher;
    private HashMap<String, Object> hsJFrameComponent;
    
    private InitAddMailAccount() {
    }
    
    public static InitAddMailAccount getInstance() {
        return InitAddMailAccountHolder.INSTANCE;
    }

    @Override
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void setJComponent(HashMap<String, Object> hsJComponant) {
        this.hsJFrameComponent = hsJComponant;
    }


    @Override
    public void execute() {
        JButton jButtonCreateMailAccount = (JButton) hsJFrameComponent.get("jButtonCreateMailAccount");
        jButtonCreateMailAccount.addActionListener(dispatcher.getListener());
        jButtonCreateMailAccount.setActionCommand("createMailAccount");
            }
    
    private static class InitAddMailAccountHolder {

        private static final InitAddMailAccount INSTANCE = new InitAddMailAccount();
    }
}
