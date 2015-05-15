/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module.ihm;

import controller.Dispatcher;
import interfaces.IActionIHM;
import java.util.HashMap;

/**
 *
 * @author Lucas
 */
public class InitUpdateAccountCS implements IActionIHM{
    
    private Dispatcher dispatcher;
    private HashMap<String, Object> hsJcomponent;

    private InitUpdateAccountCS() {
    }
    
    public static InitUpdateAccountCS getInstance() {
        return InitUpdateAccountCSHolder.INSTANCE;
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
    public boolean execute() {
        
        return true;
    }
    
    private static class InitUpdateAccountCSHolder {

        private static final InitUpdateAccountCS INSTANCE = new InitUpdateAccountCS();
    }
}
