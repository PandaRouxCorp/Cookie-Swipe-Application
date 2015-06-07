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
import model.MailAccount;
import model.User;

/**
 *
 * @author Lucas
 */
public class DeleteMailAccountAction implements IAction {

    @Override
    public boolean execute(Object... object) {
        int error;
        MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
        User user = CookieSwipeApplication.getApplication().getUser();
        error = user.deleteMailAccount(mailAccount);
        
        switch (error) {
            case CodeError.SUCESS:
                new JOptionPane().showMessageDialog(null, "Votre compte mail à bien été supprimé",
                        "Suppression d'un compte mail", JOptionPane.INFORMATION_MESSAGE);
                return true;

            case CodeError.CONNEXION_FAIL:
            case CodeError.STATEMENT_EXECUTE_FAIL:
            case CodeError.STATEMENT_CLOSE_FAIL:
                new JOptionPane().showMessageDialog(null, "Problème de connexion au serveur\nCode erreur : " + error,
                        "Suppression d'un compte mail", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                new JOptionPane().showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                        "Suppression d'un compte mail", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
}
