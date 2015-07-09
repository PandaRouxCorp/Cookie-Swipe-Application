/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.AbstractIHMAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Yehouda
 */
public class CreateAccountFrameInitializer extends AbstractIHMAction{
    
    
    private final CookieSwipeFrame frame;
            
    public CreateAccountFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
        frame = csFrame;
        System.out.println("");
    }
    
    @Override
    public boolean execute(Object ... object) {
        Dispatcher dispatcher = new Dispatcher();
        CookieSwipeButton validate = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonValidate");
        validate.addActionListener(dispatcher);
        validate.setActionCommand(ActionName.createAccount);
        
        CookieSwipeButton cancel = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonCancel");
        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        return true;
    }
}
