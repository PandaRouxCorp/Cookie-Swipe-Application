/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module;

import controller.Dispacher;
import interfaces.IJFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Gestion de l'affichage du cas d'utilisation "Gestion des échanges"
 * @author Mary
 */
public class ActionManageMailAccount{

    private Dispacher dispacher;
    
    /**
     * Constructeur de la classe permetant de s'intégrer dans l'application
     * @param frame fenêtre accèsible par notre action
     * @param dispacher classe à utiliser afin d'excécuter les traitements
     */
    public ActionManageMailAccount(IJFrame frame, Dispacher dispacher){
        
        this.dispacher = dispacher;
        JMenuBar menubar = frame.getJMenuBar();
        JMenu menuManageMailAccount = new JMenu("Gèrer compte courriel");
        
        JMenuItem addMailAccount = new JMenuItem("Ajouter un comtpe courriel");
        addMailAccount.setActionCommand("addMailAccount");
        addMailAccount.addActionListener(dispacher.getListener());
        
        JMenuItem updateMailAccount = new JMenuItem("Modifier un compte courriel");
        updateMailAccount.setActionCommand("updateMailAccount");
        updateMailAccount.addActionListener(dispacher.getListener());
        
        JMenuItem deleteMaiLAccount = new JMenuItem("Supprimer un comtpe courriel");
        deleteMaiLAccount.setActionCommand("deleteMailAccount");
        deleteMaiLAccount.addActionListener(dispacher.getListener());
        
        menuManageMailAccount.add(addMailAccount);
        menuManageMailAccount.add(updateMailAccount);
        menuManageMailAccount.add(deleteMaiLAccount);
        menubar.add(menuManageMailAccount);
        frame.refresh();
    }
 
}
