/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import errorMessage.CodeError;
import interfaces.IAction;
import javax.swing.JOptionPane;
import model.User;
import utils.Util;

/**
 *
 * @author Yehouda
 */
public class CreateCSAccountAction implements IAction {

    @Override
    public boolean execute(Object... object) {
        String login = (String) object[0];
        String password = (String) object[1];
        String backup = (String) object[2];
        
        if(!Util.isWellFormedMail(backup)) {
            JOptionPane.showMessageDialog(null, "Votre mail est mal écrit",
                                        "Création à Cookie Swipe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        User u = new User(login, password, backup);
        int code = u.create();
        new JOptionPane();
        if(code == CodeError.SUCESS) {
//            new JOptionPane();
            JOptionPane.showMessageDialog(null, "La création du compte à été effectué",
                                        "Création à Cookie Swipe", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
//            new JOptionPane();
            JOptionPane.showMessageDialog(null, "La création du compte a échoué",
                                        "Création à Cookie Swipe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
}
