/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module.ihm;

import interfaces.AbstractIHMAction;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Lucas
 */
public class UpdateAccountCSFrameInitializer extends AbstractIHMAction {

    public UpdateAccountCSFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    
    @Override
    public boolean execute(Object ... object) {
        
        return true;
    }
    
}
