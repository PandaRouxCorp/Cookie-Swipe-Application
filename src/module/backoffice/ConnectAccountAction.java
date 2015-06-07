/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import errorMessage.CodeError;
import interfaces.IAction;
import javax.swing.JOptionPane;
import model.User;

/**
 *
 * @author Lucas
 */
public class ConnectAccountAction implements IAction {

    @Override
    public boolean execute(Object... object) {
        String login = (String) object[0];
        String password = (String) object[1];

        User u = CookieSwipeApplication.getApplication().getUser();
        u.setLoginAdressMail(login);
        u.setPassword(password);

        if(u.connect() != CodeError.SUCESS) {
            new JOptionPane().showMessageDialog(null, "Connexion impossible, merci de vérifier votre login",
                    "Connexion à Cookie Swipe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
