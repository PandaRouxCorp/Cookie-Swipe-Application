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
public class InitWriteMail implements IActionIHM {

    private Dispatcher dispatcher;
    private HashMap<String, Object> hsJFrameComponent;
    private User user;

    private InitWriteMail() {
    }

    public static InitWriteMail getInstance() {
        return InitWriteMailHolder.INSTANCE;
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
    public boolean execute(Object ... object) {
        CookieSwipeButton button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonSend");
        button.setActionCommand(ActionName.sendMail);
        button.addActionListener(dispatcher.getListener());

        return true;
    }

    private static class InitWriteMailHolder {

        private static final InitWriteMail INSTANCE = new InitWriteMail();
    }
}
