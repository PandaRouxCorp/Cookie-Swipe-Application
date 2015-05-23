/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.IActionIHM;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import model.Encryption;
import model.MailAccount;
import model.User;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeTextField;

/**
 *
 * @author Lucas
 */
public class InitUpdateMailAccount implements IActionIHM {

    private Dispatcher dispatcher;
    private HashMap<String, Object> hsJFrameComponent;
    private User user;

    private InitUpdateMailAccount() {
    }

    public static InitUpdateMailAccount getInstance() {
        return InitUpdateMailAccountHolder.INSTANCE;
    }

    @Override
    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void setJComponent(HashMap<String, Object> hsJComponant) {
        this.hsJFrameComponent = hsJComponant;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean execute(Object... object) {
        MailAccount mailAccount = (MailAccount) dispatcher.getParam("mailAccountSelected");
        CookieSwipeTextField textField = (CookieSwipeTextField) hsJFrameComponent.get("cookieSwipeTextFieldNameAcountMail");
        textField.setText(mailAccount.getCSName());
        textField = (CookieSwipeTextField) hsJFrameComponent.get("cookieSwipeTextFieldMailAddress");
        textField.setText(mailAccount.getAddress());
        JPasswordField passwordField = (JPasswordField) hsJFrameComponent.get("cookieSwipePasswordFieldPasswordAccountMail");
        JComboBox color = (JComboBox) hsJFrameComponent.get("jComboBoxColor");
        color.setSelectedItem(mailAccount.getColor());
        try {
            passwordField.setText(new Encryption().decrypt(mailAccount.getPassword()));
        } catch (Exception ex) {
            Logger.getLogger(InitUpdateMailAccount.class.getName()).log(Level.SEVERE, null, ex);
        }

        CookieSwipeButton button = (CookieSwipeButton) hsJFrameComponent.get("cookieSwipeButtonValidate");
        button.setText("Modifier");
        button.addActionListener(dispatcher.getListener());
        button.setActionCommand(ActionName.udpateMailAccount);

        return true;
    }

    private static class InitUpdateMailAccountHolder {

        private static final InitUpdateMailAccount INSTANCE = new InitUpdateMailAccount();
    }
}
