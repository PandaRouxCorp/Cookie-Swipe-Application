/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookie.swipe.application;

import controller.Dispatcher;
import model.Encryption;

/**
 * Point de démarage de l'application servant de controlleur pour le moment
 *
 * @author Mary
 */
public class CookieSwipeApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        Dispatcher dispatcher = Dispatcher.getInstance();
        for(int i : new int[5]){
            System.err.println(new Encryption().encrypt("panda"));
        }

    }

}
