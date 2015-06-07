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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Encryption;
import model.MailAccount;
import model.User;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Lucas
 */
public class UpdateMailAccountAction extends AbstractIHMAction {

    public UpdateMailAccountAction(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        int error;
        MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
        String CSName = ((JTextField) hsJcomponent.get("cookieSwipeTextFieldNameAcountMail")).getText();
        mailAccount.setCSName(CSName);
        String mailAdress = ((JTextField) hsJcomponent.get("cookieSwipeTextFieldMailAddress")).getText();
        error = mailAccount.setAddress(mailAdress);
        String password = new String(((JPasswordField) hsJcomponent.get("cookieSwipePasswordFieldPasswordAccountMail")).getPassword());
        try {
            mailAccount.setPassword(new Encryption().encrypt(password));
        } catch (Exception ex) {
            Logger.getLogger(UpdateMailAccountAction.class.getName()).log(Level.SEVERE, null, ex);
            error = CodeError.ENCRYPTION_FAIL;
        }
        String color = (String) ((JComboBox) hsJcomponent.get("jComboBoxColor")).getSelectedItem();
        mailAccount.setColor(color);

        if (error == 0) {
            User user = CookieSwipeApplication.getApplication().getUser();
            error = user.updatemailAccount(mailAccount);
        }
        switch (error) {
            case CodeError.SUCESS:
                new JOptionPane().showMessageDialog(null, "Votre compte mail à bien été modifié",
                        "Modification d'un compte mail", JOptionPane.INFORMATION_MESSAGE);
                return true;

            case CodeError.CONNEXION_FAIL:
            case CodeError.STATEMENT_EXECUTE_FAIL:
            case CodeError.STATEMENT_CLOSE_FAIL:
                new JOptionPane().showMessageDialog(null, "Problème de connexion au serveur\nCode erreur : " + error,
                        "Modification d'un compte mail", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                new JOptionPane().showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                        "Modification d'un compte mail", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
}
