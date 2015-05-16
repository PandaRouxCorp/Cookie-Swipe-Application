/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import controller.Dispatcher;
import interfaces.IActionBackOffice;
import java.util.HashMap;
import javax.swing.JOptionPane;
import model.User;
import view.MainCSFrame;
import view.component.CookieSwipeLabel;
import view.component.CookieSwipePasswordField;
import view.component.CookieSwipeTextField;

/**
 *
 * @author Lucas
 */
public class LoginAccount implements IActionBackOffice {

    private User user;
    private Dispatcher dispatcher;
    private HashMap<String, Object> hsJcomponent;

    private LoginAccount() {
    }

    public static LoginAccount getInstance() {
        return LoginAccountHolder.INSTANCE;
    }

    @Override
    public void setJComponent(HashMap<String, Object> hsJComponant) {
        this.hsJcomponent = hsJComponant;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean execute() {

        System.err.println(user);

        CookieSwipeTextField textField = (CookieSwipeTextField) hsJcomponent.get("cookieSwipeTextFieldLogin");
        user.setLoginAdressMail(textField.getText());
        CookieSwipePasswordField passwordField = (CookieSwipePasswordField) hsJcomponent.get("cookieSwipePasswordFieldPassword");
        user.setPassword(new String(passwordField.getPassword()));

        if (user.connect() == null) {
            new JOptionPane().showMessageDialog(null, "Connexion impossible, merci de vérifier votre login",
                    "Connexion à Cookie Swipe", JOptionPane.ERROR_MESSAGE);
            return false; 
        }
        System.err.println(user);
        return true;
    }

    private static class LoginAccountHolder {

        private static final LoginAccount INSTANCE = new LoginAccount();
    }
}
