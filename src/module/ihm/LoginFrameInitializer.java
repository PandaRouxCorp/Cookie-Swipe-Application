/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.AbstractIHMAction;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Lucas
 */
public class LoginFrameInitializer extends AbstractIHMAction {

    public LoginFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
    }
    
    @Override
    public boolean execute(Object ... object) {
        Dispatcher dispatcher = new Dispatcher();
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonLogin");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.logAccount);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonSendLogin");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.forgottenLogin);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonSendPassword");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.forgottenPassword);
        button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonInscription");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.insciption);
        return true;
    }

}
