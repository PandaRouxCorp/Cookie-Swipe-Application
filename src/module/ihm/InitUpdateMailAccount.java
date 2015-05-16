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
import javax.swing.JButton;
import model.User;
import view.component.CookieSwipeButton;

/**
 *
 * @author Lucas
 */
public class InitUpdateMailAccount implements IActionIHM{
    
    private Dispatcher dispatcher;
    private HashMap<String, Object> hsJFrameComponent;
    private User user;
   
    private InitUpdateMailAccount() {
    }
    
    public static InitUpdateMailAccount getInstance() {
        return InitUpdateMailAccountHolder.INSTANCE;
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
    public void setUser(User user) {
        this.user = user;
    }

   @Override
    public boolean execute() {
        CookieSwipeButton button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonValidate");
        button.setText("Modifier");
        button.addActionListener(dispatcher.getListener());
        button.setActionCommand(ActionName.udpateMailAccount);
    
        return true;
    }
   
    private static class InitUpdateMailAccountHolder {

        private static final InitUpdateMailAccount INSTANCE = new InitUpdateMailAccount();
    }
}
