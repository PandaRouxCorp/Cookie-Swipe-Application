/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import cookie.swipe.application.CookieSwipeApplication;
import interfaces.AbstractIHMAction;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Yehouda
 */
public class BlacklistFrameInitializer extends AbstractIHMAction {

    public BlacklistFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeDeleteFromBlacklist");
        button.setActionCommand(ActionName.removeBlacklistSender);
        button.addActionListener(new Dispatcher());
        
        return true;
    }
    
}
