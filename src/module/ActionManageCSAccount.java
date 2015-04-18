/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.IJFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Doit être transformer en singleton
 * Gestion de l'affichage du cas d'utilisation "Gestion du compte Cookie Swipe"
 * @author Mary
 */


public class ActionManageCSAccount {
    
    private Dispatcher dispatcher;
    
    /**
     * Constructeur de la classe permetant de s'intégrer dans l'application
     * @param frame fenêtre accèsible par notre action
     * @param dispatcher classe à utiliser afin d'excécuter les traitements
     */
    public ActionManageCSAccount(IJFrame frame, Dispatcher dispatcher){
    
        this.dispatcher = dispatcher;
        JMenuBar menubar = frame.getJMenuBar();
        JMenu menuManageCSAccount = new JMenu("Mon compte");
        
        JMenuItem updateCSAccount = new JMenuItem("Modifier mon compte");
        updateCSAccount.addActionListener(dispatcher.getListener());
        updateCSAccount.setActionCommand(ActionName.updateAccount);
        
        JMenuItem logout = new JMenuItem("Me deconnecter");
        logout.addActionListener(dispatcher.getListener());
        logout.setActionCommand(ActionName.logout);
        
        menuManageCSAccount.add(updateCSAccount);
        menuManageCSAccount.add(logout);
        menubar.add(menuManageCSAccount);
        frame.refresh();
    }
    
}
