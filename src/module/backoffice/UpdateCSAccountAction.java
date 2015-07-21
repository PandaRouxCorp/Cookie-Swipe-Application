/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import errorMessage.CodeError;
import interfaces.AbstractIHMAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Encryption;
import model.User;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Lucas
 */
public class UpdateCSAccountAction extends AbstractIHMAction {

    public UpdateCSAccountAction(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        int error = 0;
        User user = CookieSwipeApplication.getApplication().getUser();
        String loginAdressMail = ((JTextField) hsJcomponent.get("cookieSwipeTextFieldLoginAdressMail")).getText();
        user.setLoginAdressMail(loginAdressMail);
        String mailAdress = ((JTextField) hsJcomponent.get("cookieSwipeTextFieldBackupMail")).getText();
        user.setBackupMail(mailAdress);
        String password = new String(((JPasswordField) hsJcomponent.get("cookieSwipePasswordFieldPassword")).getPassword());
        try {
            user.setPassword(password);
        } catch (Exception ex) {
            Logger.getLogger(UpdateMailAccountAction.class.getName()).log(Level.SEVERE, null, ex);
            error = CodeError.ENCRYPTION_FAIL;
        }
        error = user.update();
       
        switch (error) {
            case CodeError.SUCESS:
                new JOptionPane().showMessageDialog(null, "Votre compte à bien été modifié",
                        "Modification du compte", JOptionPane.INFORMATION_MESSAGE);
                return true;

            case CodeError.CONNEXION_FAIL:
            case CodeError.STATEMENT_EXECUTE_FAIL:
            case CodeError.STATEMENT_CLOSE_FAIL:
                new JOptionPane().showMessageDialog(null, "Problème de connexion au serveur\nCode erreur : " + error,
                        "Modification du compte", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                new JOptionPane().showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                        "Modification du compte", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
}
