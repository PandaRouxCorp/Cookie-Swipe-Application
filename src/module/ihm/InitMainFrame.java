/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.IActionIHM;
import java.util.HashMap;
import view.component.CookieSwipeButton;

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
    public boolean execute() {
        
        CookieSwipeButton button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonUpdateCSAccount");
        button.setActionCommand(ActionName.updateAccount);
        button.addActionListener(dispatcher.getListener());
        
        button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonLogout");
        button.setActionCommand(ActionName.logout);
        button.addActionListener(dispatcher.getListener());
        
        button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonAddMailAccount");
        button.setActionCommand(ActionName.addMailAccount);
        button.addActionListener(dispatcher.getListener());
        
        button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonUpdateMailAccount");
        button.setActionCommand(ActionName.udpateMailAccount);
        button.addActionListener(dispatcher.getListener());
        
        button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonDeleteMailAccount");
        button.setActionCommand(ActionName.deleteMailAccount);
        button.addActionListener(dispatcher.getListener());
        
        
        button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonNewMail");
        button.setActionCommand(ActionName.writeMail);
        button.addActionListener(dispatcher.getListener());
        
        
        button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonAnswer");
        button.setActionCommand(ActionName.answerMail);
        button.addActionListener(dispatcher.getListener());
        
        
        button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonDeleteMail");
        button.setActionCommand(ActionName.deleteMail);
        button.addActionListener(dispatcher.getListener());
        
        
        button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonForward");
        button.setActionCommand(ActionName.forwardMail);
        button.addActionListener(dispatcher.getListener());
        
        
        return true;
             
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
