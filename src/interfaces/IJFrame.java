/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import javax.swing.JMenuBar;

/**
 * Interface permetant de partager les composants d'une fenêtre et de la la rafraichir 
 * @author Mary
 */
public interface IJFrame {
    
    public JMenuBar getJMenuBar(); 

    public void setVisible(boolean b);
    
    public void refresh();

}
