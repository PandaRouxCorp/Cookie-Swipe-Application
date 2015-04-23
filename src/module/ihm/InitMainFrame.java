/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.IActionIHM;
import interfaces.IJFrame;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Lucas
 */
public class InitMainFrame implements IActionIHM{
    
    private HashMap<String, Object> hsJFrameComponent;
    private Dispatcher dispatcher;
    
    private InitMainFrame() {
    }
    
    public static InitMainFrame getInstance() {
        return InitMainFrameHolder.INSTANCE;
    }
 
    private static class InitMainFrameHolder {

        private static final InitMainFrame INSTANCE = new InitMainFrame();
    }

    @Override
    public void execute() {
        JMenuBar menubar = (JMenuBar) hsJFrameComponent.get("JMenuBarMainFrame");
        JMenu menuManageCSAccount = new JMenu("Mon compte");
        
        JMenuItem updateCSAccount = new JMenuItem("Modifier mon compte");
        updateCSAccount.addActionListener(dispatcher.getListener());
        updateCSAccount.setActionCommand(ActionName.updateAccount);
        
        JMenuItem logout = new JMenuItem("Me deconnecter");
        logout.addActionListener(dispatcher.getListener());
        logout.setActionCommand(ActionName.logout);
        
        menuManageCSAccount.add(updateCSAccount);
        menuManageCSAccount.add(logout);
        menubar.add(menuManageCSAccount);
                
        JMenu menuManageMailAccount = new JMenu("Gèrer compte courriel");
        
        JMenuItem addMailAccount = new JMenuItem("Ajouter un compte courriel");
        addMailAccount.setActionCommand(ActionName.addMailAccount);
        addMailAccount.addActionListener(dispatcher.getListener());
        
        JMenuItem updateMailAccount = new JMenuItem("Modifier un compte courriel");
        updateMailAccount.setActionCommand(ActionName.udpateMailAccount);
        updateMailAccount.addActionListener(dispatcher.getListener());
        
        JMenuItem deleteMaiLAccount = new JMenuItem("Supprimer un compte courriel");
        deleteMaiLAccount.setActionCommand(ActionName.deleteMailAccount);
        deleteMaiLAccount.addActionListener(dispatcher.getListener());
        
        menuManageMailAccount.add(addMailAccount);
        menuManageMailAccount.add(updateMailAccount);
        menuManageMailAccount.add(deleteMaiLAccount);
        menubar.add(menuManageMailAccount);
        
        JButton send = new JButton("Nouveau courriel");
        send.addActionListener(dispatcher.getListener());
        send.setActionCommand("newMail");
        menubar.add(send);
             
    }
   
    @Override
    public void setJComponent(HashMap<String, Object> hsJComponant) {
        this.hsJFrameComponent = hsJComponant;
    }

    @Override
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
    
    
}
