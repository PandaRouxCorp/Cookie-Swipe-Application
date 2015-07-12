/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import dao.DAOUser;
import interfaces.IAction;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import model.MailAccount;
import model.User;
import module.ihm.WriteMailFrameInitializer;
import view.MailCSFrame;

/**
 *
 * @author Yehouda
 */
public class MailAction implements IAction {

    @Override
    public boolean execute(Object... object) {
        try {
            String action = (String) object[0];
            Message message = (Message) object[1];
            
            MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
            String startSubject = "";
            switch(action) {
                case "forward":
                    startSubject = "FW : ";
                case "reply":
                case "replyAll":
                    MailCSFrame mailFrame = new MailCSFrame();
                    try {
                        if(action.startsWith("reply")) {
                            startSubject = "RE : ";
                            mailFrame.setCookieSwipeTextFieldTo(Arrays.toString(message.getFrom()));
                        }
                        mailFrame.setCookieSwipeTextFieldSubject(startSubject + message.getSubject());
                        mailFrame.setjTextAreaMail(mailTranfer(message));
                    } catch (MessagingException | IOException ex) {
                        Logger.getLogger(MailAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    CookieSwipeApplication.getApplication().setFocusFrame(mailFrame);
                    new WriteMailFrameInitializer(mailFrame).execute();
                    
                    break;
                case "addBlackListSender":
                    break;
                case "archive":
                    // TODO
                    System.err.println("NOT IMPLEMENTED");
                    break;
            }
            return true;
        } catch (MessagingException ex) {
            Logger.getLogger(MailAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private String mailTranfer(Message message) throws MessagingException, IOException {
        return "\n\n\n----------------------------------------\n"
                + "From : " + Arrays.toString(message.getFrom()) + "\n"
                + "To : " + Arrays.toString(message.getRecipients(Message.RecipientType.TO)) + "\n"
                + "Date : " + message.getReceivedDate() + "\n\n"
                + ReadMailAction.getContentMessage(message)
                + "\n\n";
    }
}
