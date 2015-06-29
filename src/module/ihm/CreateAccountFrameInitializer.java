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
 * @author Yehouda
 */
public class CreateAccountFrameInitializer extends AbstractIHMAction{
    
    public CreateAccountFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
        System.out.println("");
    }
    
    @Override
    public boolean execute(Object ... object) {
        Dispatcher dispatcher = new Dispatcher();
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonValidate");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.createAccount);
        return true;
    }
}
