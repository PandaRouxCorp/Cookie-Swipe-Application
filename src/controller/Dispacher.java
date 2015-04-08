/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe permetant d'attribuer les action déclancher par l'interface utilisateur à des traitements
 * @author Lucas
 */
public class Dispacher {
    
    public ActionListener getListener(){
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendAction(e);
            }
        };
    }
    public void sendAction(ActionEvent e){        
            System.out.println(e.getActionCommand());
    }
}
