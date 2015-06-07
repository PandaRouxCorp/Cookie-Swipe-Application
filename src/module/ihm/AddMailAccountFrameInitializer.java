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
public class AddMailAccountFrameInitializer extends AbstractIHMAction {

    public AddMailAccountFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object ... object) {
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonValidate");
        button.setText("Créer");
        button.setActionCommand(ActionName.createMailAccount);
        button.addActionListener(new Dispatcher());
        return true;
    }
}
