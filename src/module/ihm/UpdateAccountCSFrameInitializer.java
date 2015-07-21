/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import cookie.swipe.application.CookieSwipeApplication;
import interfaces.AbstractIHMAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPasswordField;
import model.Encryption;
import model.User;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeTextField;

/**
 *
 * @author Lucas
 */
public class UpdateAccountCSFrameInitializer extends AbstractIHMAction {

    public UpdateAccountCSFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        User user = CookieSwipeApplication.getApplication().getUser();
        CookieSwipeTextField textField = (CookieSwipeTextField) hsJcomponent.get("cookieSwipeTextFieldLoginAdressMail");
        textField.setText(user.getLoginAdressMail());
        textField = (CookieSwipeTextField) hsJcomponent.get("cookieSwipeTextFieldBackupMail");
        textField.setText(user.getBackupMail());
        JPasswordField passwordField = (JPasswordField) hsJcomponent.get("cookieSwipePasswordFieldPassword");
        try {
            passwordField.setText(new Encryption().decrypt(user.getPassword()));
        } catch (Exception ex) {
            Logger.getLogger(UpdateMailAccountFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonValidate");
        button.setText("Modifier");
        button.addActionListener(new Dispatcher());
        button.setActionCommand(ActionName.updateCSAccount);

        return true;
    }

}
