/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import interfaces.IJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import module.ActionManageMailAccount;
import view.AddMailAccount;

/**
 * Classe permetant d'attribuer les action déclancher par l'interface utilisateur à des traitements
 * Doit être un singleton
 * @author Mary
 */
public class Dispatcher {
    
    private IJFrame frame;
    ActionManageMailAccount manager = new ActionManageMailAccount(this);
    //public Dispatcher()
    //La j'ai mon dispatcher, c'est lui qui va lancer les méthodes qui corresponde au différent bouton de l'application
    public ActionListener getListener(){
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendAction(e);
            }
        };
    }
    public void sendAction(ActionEvent e){        
    
        //System.out.println(e.getActionCommand());
        String action = e.getActionCommand();
        
        switch(action){
            case ActionName.createAccount: 
                System.out.println(action); 
                break;
            case ActionName.logAccount: 
                System.out.println(action); 
                break;
            case ActionName.updateAccount: 
                System.out.println(action); 
                break;
            case ActionName.forgottenPassword: 
                System.out.println(action); 
                break;
            case ActionName.forgottenLogin: 
                System.out.println(action); 
                break;
            case ActionName.addMailAccount: 
                System.err.println(action);
                frame = new AddMailAccount();
                frame.setVisible(true);
                manager.addMailAccount(frame);
                break;
            case ActionName.selectMailAccount: 
                System.out.println(action); 
                break;
            case ActionName.udpateMailAccount: 
                System.out.println(action); 
                break;
            case ActionName.deleteMailAccount: 
                System.out.println(action); 
                break;
            case ActionName.writeMail: 
                System.out.println(action); 
                break;
            case ActionName.readMail: 
                System.out.println(action); 
                break;
            case ActionName.selectMail: 
                System.out.println(action); 
                break;
            case ActionName.deleteMail: 
                System.out.println(action); 
                break;
            case ActionName.addBlackListSender: 
                System.out.println(action); 
                break;
            case ActionName.removeBlacklistSender: 
                System.out.println(action); 
                break;
            case ActionName.answerMail: 
                System.out.println(action); 
                break;
            case ActionName.forwardMail: 
                System.out.println(action); 
                break;
            case ActionName.sendMail: 
                System.out.println(action); 
                break;
            case ActionName.downloadPicture: 
                System.out.println(action); 
                break;
            case ActionName.createMailAccount:
                
                //Ici quand je valide un compte ma fenêtre va m'envoyé ici
                //D'ici j'ai pas de référence à ce qui est fait dans ma fenêtre
                //Je sais pas ou créer mon objet MailAccount et où le manipuler
                JTextField result = (JTextField) frame.getJComponent().get("jTextFieldMailAdress");
                System.err.println(result.getText());
                result = (JTextField) frame.getJComponent().get("jTextFieldMailPassword");
                System.err.println(result.getText());
                result = (JTextField) frame.getJComponent().get("jTextFieldNameMailAccount");
                System.err.println(result.getText());
                frame.setVisible(false);               
                
            default :
                break;
        }
    }
}

