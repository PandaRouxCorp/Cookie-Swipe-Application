/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import errorMessage.CodeError;
import interfaces.AbstractIHMAction;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.User;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Lucas
 */
public class CreateMailAccountAction extends AbstractIHMAction {

    public CreateMailAccountAction(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        String CSName = ((JTextField) hsJcomponent.get("cookieSwipeTextFieldNameAcountMail")).getText();
        String mailAdress = ((JTextField) hsJcomponent.get("cookieSwipeTextFieldMailAddress")).getText();
        String password = new String(((JPasswordField) hsJcomponent.get("cookieSwipePasswordFieldPasswordAccountMail")).getPassword());
        //String color = (String) ((JComboBox<?>) hsJcomponent.get("jComboBoxColor")).getSelectedItem();
        User user = CookieSwipeApplication.getApplication().getUser();
        int error = user.addNewMailAccount(CSName, mailAdress, password);
        switch (error) {
            case CodeError.SUCESS:
                JOptionPane.showMessageDialog(null, "Votre compte mail à bien été ajouté",
                        "Ajout d'un nouveau compte mail", JOptionPane.INFORMATION_MESSAGE);
                return true;
            case CodeError.CONNEXION_FAIL:
            	JOptionPane.showMessageDialog(null, "Accès au compte impossible. Veuillez vérifier vos informations",
                        "Ajout d'un nouveau compte mail", JOptionPane.INFORMATION_MESSAGE);
            	break;
            case CodeError.STATEMENT_EXECUTE_FAIL:
            case CodeError.STATEMENT_CLOSE_FAIL:
                JOptionPane.showMessageDialog(null, "Problème de connexion au serveur\nCode erreur : " + error,
                        "Ajout d'un nouveau compte mail", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                        "Ajout d'un nouveau compte mail", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
}
