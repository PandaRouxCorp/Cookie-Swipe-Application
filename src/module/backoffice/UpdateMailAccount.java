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
public class UpdateMailAccount implements IActionBackOffice {

    private User user;
    private HashMap<String, Object> hsJCompment;
    private Dispatcher dispatcher;

    private UpdateMailAccount() {
    }

    public static UpdateMailAccount getInstance() {
        return updateMailAccountAccountHolder.INSTANCE;
    }

    @Override
    public boolean execute(Object... object) {
        int error;
        MailAccount mailAccount = (MailAccount) dispatcher.getParam("mailAccountSelected");
        String CSName = ((JTextField) hsJCompment.get("cookieSwipeTextFieldNameAcountMail")).getText();
        mailAccount.setCSName(CSName);
        String mailAdress = ((JTextField) hsJCompment.get("cookieSwipeTextFieldMailAddress")).getText();
        error = mailAccount.setAddress(mailAdress);
        String password = new String(((JPasswordField) hsJCompment.get("cookieSwipePasswordFieldPasswordAccountMail")).getPassword());
        try {
            mailAccount.setPassword(new Encryption().encrypt(password));
        } catch (Exception ex) {
            Logger.getLogger(UpdateMailAccount.class.getName()).log(Level.SEVERE, null, ex);
            error = CodeError.ENCRYPTION_FAIL;
        }
        String color = (String) ((JComboBox) hsJCompment.get("jComboBoxColor")).getSelectedItem();
        mailAccount.setColor(color);

        if (error == 0) {
            error = user.updatemailAccount(mailAccount);
        }
        switch (error) {
            case CodeError.SUCESS:
                new JOptionPane().showMessageDialog(null, "Votre compte mail à bien été modifié",
                        "Modification d'un nouveau compte mail", JOptionPane.INFORMATION_MESSAGE);
                return true;

            case CodeError.CONNEXION_FAIL:
            case CodeError.STATEMENT_EXECUTE_FAIL:
            case CodeError.STATEMENT_CLOSE_FAIL:
                new JOptionPane().showMessageDialog(null, "Problème de connexion au serveur\nCode erreur : " + error,
                        "Modification d'un nouveau compte mail", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                new JOptionPane().showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                        "Modification d'un nouveau compte mail", JOptionPane.ERROR_MESSAGE);

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

    private static class updateMailAccountAccountHolder {

        private static final UpdateMailAccount INSTANCE = new UpdateMailAccount();
    }

}
