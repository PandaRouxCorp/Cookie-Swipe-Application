/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module;

import controller.Dispatcher;
import interfaces.IJFrame;
import javax.swing.JButton;
import javax.swing.JMenuBar;

/**
 * Doit être transformer en singleton
 * Gestion de l'affichage du cas d'utilisation "Gestion des échanges" 
 * @author Mary
 */
public class ActionManageEchange {
    
    private Dispatcher dispatcher;
    
    /**
     * Constructeur de la classe permetant de s'intégrer dans l'application
     * @param frame fenêtre accèsible par notre action
     * @param dispatcher classe à utiliser afin d'excécuter les traitements
     */
    public ActionManageEchange(IJFrame frame, Dispatcher dispatcher){
        
        this.dispatcher = dispatcher;
        JMenuBar menubar = (JMenuBar) frame.getJComponent().get("JMenuBarMainFrame");
        
        JButton send = new JButton("Nouveau courriel");
        send.addActionListener(dispatcher.getListener());
        send.setActionCommand("newMail");
        menubar.add(send);
        
        frame.refresh();
    }
}
