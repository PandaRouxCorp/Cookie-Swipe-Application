/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cookie.swipe.application;

import controller.Dispatcher;
import interfaces.IJFrame;
import module.ActionManageCSAccount;
import module.ActionManageEchange;
import module.ActionManageMailAccount;
import view.MainJFrame;

/**
 * Point de démarage de l'application servant de controlleur pour le moment
 * @author Mary
 */
public class CookieSwipeApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         
        //IJFrame j = new MainJFrame();
        Dispatcher dispatcher = Dispatcher.getInstance();
        /*j.setVisible(true);
        ActionManageCSAccount CSAccount = new ActionManageCSAccount(dispatcher,j );
        ActionManageMailAccount mailAccount = new ActionManageMailAccount(j, dispatcher);
        ActionManageEchange echange = new ActionManageEchange(j, dispatcher);
        */
    }
    
    
}
