/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module;

import controller.Dispacher;
import interfaces.IJFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;

/**
 * Gestion de l'affichage du cas d'utilisation "Gestion des échanges" 
 * @author Mary
 */
public class ActionManageEchange {
    
    private Dispacher dispacher;
    
    /**
     * Constructeur de la classe permetant de s'intégrer dans l'application
     * @param frame fenêtre accèsible par notre action
     * @param dispacher classe à utiliser afin d'excécuter les traitements
     */
    public ActionManageEchange(IJFrame frame, Dispacher dispacher){
        
        this.dispacher = dispacher;
        JMenuBar menubar = frame.getJMenuBar();
        
        JButton send = new JButton("Nouveau courriel");
        send.addActionListener(dispacher.getListener());
        send.setActionCommand("newMail");
        menubar.add(send);
        
        frame.refresh();
    }
}
