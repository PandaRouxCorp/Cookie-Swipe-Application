/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import interfaces.IAction;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
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
            MailCSFrame frame = new MailCSFrame();
            // set frame
//            frame.setCookieSwipeTextFieldSubject( message.getSubject() );
            frame.setCookieSwipeTextFieldTo(Arrays.toString(message.getFrom()));
            frame.setjTextAreaMail("the text that i decided");
            // then focus it
            application.setFocusFrame(frame);
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(ReadMailAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
