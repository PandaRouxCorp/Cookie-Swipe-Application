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
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import model.Encryption;
import model.MailAccount;
import view.component.CookieSwipeButton;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipeTextField;

/**
 *
 * @author Lucas
 */
public class UpdateMailAccountFrameInitializer extends AbstractIHMAction {

    public UpdateMailAccountFrameInitializer(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
        CookieSwipeTextField textField = (CookieSwipeTextField) hsJcomponent.get("cookieSwipeTextFieldNameAcountMail");
        textField.setText(mailAccount.getCSName());
        textField = (CookieSwipeTextField) hsJcomponent.get("cookieSwipeTextFieldMailAddress");
        textField.setText(mailAccount.getAddress());
        JPasswordField passwordField = (JPasswordField) hsJcomponent.get("cookieSwipePasswordFieldPasswordAccountMail");
        JComboBox color = (JComboBox) hsJcomponent.get("jComboBoxColor");
        color.setSelectedItem(mailAccount.getColor());
        try {
            passwordField.setText(new Encryption().decrypt(mailAccount.getPassword()));
        } catch (Exception ex) {
            Logger.getLogger(UpdateMailAccountFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        CookieSwipeButton button = (CookieSwipeButton) hsJcomponent.get("cookieSwipeButtonValidate");
        button.setText("Modifier");
        button.addActionListener(new Dispatcher());
        button.setActionCommand(ActionName.updateMailAccount);

        return true;
    }

}
