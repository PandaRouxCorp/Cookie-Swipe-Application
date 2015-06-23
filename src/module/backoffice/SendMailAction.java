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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Encryption;
import model.Mail;
import model.MailAccount;
import view.component.CookieSwipeFrame;

/**
 *
 * @author Yehouda
 */
public class SendMailAction extends AbstractIHMAction {

    public SendMailAction(CookieSwipeFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        int error = 0;
        MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
        if(mailAccount != null) {
            Mail mail = mailAccount.createNewMail();
            mail.setTo( ((JTextField) hsJcomponent.get("cookieSwipeTextFieldTo")).getText() );
            // manque le subject du mail dans l'ihm
//            mail.setSubject(((JTextField) hsJcomponent.get("cookieSwipeSubjectFieldTo")).getText() );
            mail.setBody( ((JTextArea) hsJcomponent.get("jTextAreaMail")).getText() );
            mailAccount.sendMail();
        }
        
        switch (error) {
            case CodeError.SUCESS:
                new JOptionPane().showMessageDialog(null, "Votre compte mail à bien été envoyé",
                        "Envoi d'un mail", JOptionPane.INFORMATION_MESSAGE);
                return true;

            case CodeError.CONNEXION_FAIL:
            case CodeError.STATEMENT_EXECUTE_FAIL:
            case CodeError.STATEMENT_CLOSE_FAIL:
                new JOptionPane().showMessageDialog(null, "Problème de connexion au serveur\nCode erreur : " + error,
                        "Envoi d'un mail", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                new JOptionPane().showMessageDialog(null, "Un problème est survenu\nCode erreur : " + error,
                        "Envoi d'un mail", JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }
}