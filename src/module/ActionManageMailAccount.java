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
 * Doit être transformer en singleton
 * Gestion de l'affichage du cas d'utilisation "Gestion des échanges"
 * @author Mary
 */
public class ActionManageMailAccount{

    private Dispatcher dispatcher;
    
    /**
     * Constructeur de la classe permetant de s'intégrer dans l'application
     * @param frame fenêtre accèsible par notre action
     * @param dispatcher classe à utiliser afin d'excécuter les traitements
     */
    public ActionManageMailAccount(IJFrame frame, Dispatcher dispatcher){
        
        this.dispatcher = dispatcher;
        JMenuBar menubar = frame.getJMenuBar();
        JMenu menuManageMailAccount = new JMenu("Gèrer compte courriel");
        
        JMenuItem addMailAccount = new JMenuItem("Ajouter un compte courriel");
        addMailAccount.setActionCommand("addMailAccount");
        addMailAccount.addActionListener(dispatcher.getListener());
        
        JMenuItem updateMailAccount = new JMenuItem("Modifier un compte courriel");
        updateMailAccount.setActionCommand("updateMailAccount");
        updateMailAccount.addActionListener(dispatcher.getListener());
        
        JMenuItem deleteMaiLAccount = new JMenuItem("Supprimer un compte courriel");
        deleteMaiLAccount.setActionCommand("deleteMailAccount");
        deleteMaiLAccount.addActionListener(dispatcher.getListener());
        
        menuManageMailAccount.add(addMailAccount);
        menuManageMailAccount.add(updateMailAccount);
        menuManageMailAccount.add(deleteMaiLAccount);
        menubar.add(menuManageMailAccount);
        frame.refresh();
    }
 
}
