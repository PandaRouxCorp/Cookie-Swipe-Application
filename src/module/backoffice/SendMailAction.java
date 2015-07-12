/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import interfaces.AbstractIHMAction;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
        MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
        if(mailAccount != null) {
            Mail mail = mailAccount.createNewMail();
            mailAccount.addDestinataire( ((JTextField) hsJcomponent.get("cookieSwipeTextFieldTo")).getText() );
            mailAccount.addCopie( ((JTextField) hsJcomponent.get("cookieSwipeTextFieldToCc")).getText() );
            mailAccount.addSubject(((JTextField) hsJcomponent.get("cookieSwipeTextFieldSubject")).getText() );
            mailAccount.addBody( ((JTextArea) hsJcomponent.get("jTextAreaMail")).getText() );
            mailAccount.sendMail();
            mailAccount.clearAttachments();
        }
        return false;
    }
}