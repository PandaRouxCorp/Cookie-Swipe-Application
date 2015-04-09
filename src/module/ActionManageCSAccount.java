/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module;

import controller.Dispatcher;
import interfaces.IJFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Gestion de l'affichage du cas d'utilisation "Gestion du compte Cookie Swipe"
 * @author Mary
 */


public class ActionManageCSAccount {
    
    private Dispatcher dispacher;
    
    /**
     * Constructeur de la classe permetant de s'intégrer dans l'application
     * @param frame fenêtre accèsible par notre action
     * @param dispacher classe à utiliser afin d'excécuter les traitements
     */
    public ActionManageCSAccount(IJFrame frame, Dispatcher dispacher){
    
        this.dispacher = dispacher;
        JMenuBar menubar = frame.getJMenuBar();
        JMenu menuManageCSAccount = new JMenu("Mon compte");
        
        JMenuItem updateCSAccount = new JMenuItem("Modifier mon compte");
        updateCSAccount.addActionListener(dispacher.getListener());
        updateCSAccount.setActionCommand("updateCSAccount");
        
        JMenuItem logout = new JMenuItem("Me deconnecter");
        logout.addActionListener(dispacher.getListener());
        logout.setActionCommand("logout");
        
        menuManageCSAccount.add(updateCSAccount);
        menuManageCSAccount.add(logout);
        menubar.add(menuManageCSAccount);
        frame.refresh();
    }
    
}
