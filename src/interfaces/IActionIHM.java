/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import controller.Dispatcher;
import java.util.HashMap;

/**
 *
 * @author Lucas
 */
public interface IActionIHM {
    
    public void setDispatcher(Dispatcher dispatcher);
    
    public void setJComponent(HashMap<String, Object> hsJComponant);
    
    public boolean execute();
    
}
