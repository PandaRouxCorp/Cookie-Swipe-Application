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
import model.User;
import view.component.CookieSwipeButton;

/**
 *
 * @author Lucas
 */
public class InitLoginFrame implements IActionIHM {

    private Dispatcher dispatcher;
    private HashMap<String, Object> hsJcomponent;
    private User user;

    private InitLoginFrame() {
    }

    public static InitLoginFrame getInstance() {
        return InitLoginFrameHolder.INSTANCE;
    }

    @Override
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void setJComponent(HashMap<String, Object> hsJComponant) {
        this.hsJcomponent = hsJComponant;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public boolean execute() {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonLogin");
        button.addActionListener(dispatcher.getListener());
        button.setActionCommand(ActionName.logAccount);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonSendLogin");
        button.addActionListener(dispatcher.getListener());
        button.setActionCommand(ActionName.forgottenLogin);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonSendPassword");
        button.addActionListener(dispatcher.getListener());
        button.setActionCommand(ActionName.forgottenPassword);
        
        return true;
    
    }

    private static class InitLoginFrameHolder {

        private static final InitLoginFrame INSTANCE = new InitLoginFrame();
    }
}
