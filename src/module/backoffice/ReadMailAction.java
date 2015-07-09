/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import interfaces.IAction;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import model.MailAccount;
import view.MailCSFrame;

/**
 *
 * @author Yehouda
 */
public class ReadMailAction implements IAction {

    @Override
    public boolean execute(Object... object) {
        try {
            Message message = (Message) object[0];
            CookieSwipeApplication application = CookieSwipeApplication.getApplication();
            MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
            
            MailCSFrame frame = new MailCSFrame();
            // set frame
            frame.setCookieSwipeTextFieldSubject(message.getSubject());
            frame.setCookieSwipeTextFieldTo(Arrays.toString(message.getFrom()));
            
            System.out.println(message.getContentType());
            if (message.isMimeType("text/plain")) {
                frame.setjTextAreaMail((String) message.getContent());
            } else {
                frame.setjTextAreaMail("on vera");
            }

            // then focus it
            application.setFocusFrame(frame);
            return true;
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(ReadMailAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
