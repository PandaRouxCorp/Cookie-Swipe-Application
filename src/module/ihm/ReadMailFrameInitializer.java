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
public class ReadMailFrameInitializer extends AbstractIHMAction {

    public ReadMailFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        Dispatcher dispatcher = new Dispatcher();
        
        CookieSwipeButton addToBlacklist = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonToBlacklist");
        addToBlacklist.setActionCommand(ActionName.addBlackListSender);
        addToBlacklist.addActionListener(dispatcher);
        
        CookieSwipeButton reply = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonReply");
        reply.setActionCommand(ActionName.reply); // to change
        reply.addActionListener(dispatcher);
        
        CookieSwipeButton replyAll = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonReplyToAll");
        replyAll.setActionCommand(ActionName.replyAll); // to change
        replyAll.addActionListener(dispatcher);
        
        CookieSwipeButton forward = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonForward");
        forward.setActionCommand(ActionName.forward); // to change
        forward.addActionListener(dispatcher);
        
        return true;
    }
    
}
