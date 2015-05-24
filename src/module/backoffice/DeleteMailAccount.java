/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import controller.Dispatcher;
import errorMessage.CodeError;
import interfaces.IActionBackOffice;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.Encryption;
import model.MailAccount;
import model.User;

/**
 *
 * @author Lucas
 */
public class DeleteMailAccount implements IActionBackOffice {

    private User user;
    private HashMap<String, Object> hsJCompment;
    private Dispatcher dispatcher;

    private DeleteMailAccount() {
    }

    public static DeleteMailAccount getInstance() {
        return deleteMailAccountHolder.INSTANCE;
    }

    private static class deleteMailAccountHolder {

        private static final DeleteMailAccount INSTANCE = new DeleteMailAccount();
    }

    @Override
    public boolean execute(Object... object) {
        int error;
        MailAccount mailAccount = (MailAccount) dispatcher.getParam("mailAccountSelected");
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

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setJComponent(HashMap<String, Object> hsJCompment) {
        this.hsJCompment = hsJCompment;

    }

    @Override
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

}
