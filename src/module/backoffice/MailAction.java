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
import javax.mail.internet.MimeMessage;
import javax.swing.JTextField;
import model.Mail;
import model.MailAccount;
import module.ihm.WriteMailFrameInitializer;
import view.MailCSFrame;

/**
 *
 * @author Yehouda
 */
public class MailAction implements IAction {

    @Override
    public boolean execute(Object... object) {
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
                // TODO
                System.err.println("NOT IMPLEMENTED");
                break;
            case "archive":
                // TODO
                System.err.println("NOT IMPLEMENTED");
                break;
        }
        return true;
    }
    
    //response & forward mail
    public void response(MailAccount ma, Message message) {
//        try {
//            ma.createNewMail();
//            ma.addBody(mailTranfer(message));
//            ma.addDestinataire(message.getFrom());
//            ma.addSubject("RE: " + message.getSubject());
//        return mess;
//        currentMail = new Mail();
//        currentMail.setBody(mailTranfer(mail));
//        currentMail.setTo(mail.getFrom());
//        currentMail.setFrom(mail.getTo());
//        currentMail.setSubject("FW : " + mail.getSubject());
//        return currentMail;
//        } catch (MessagingException | IOException ex) {
//            Logger.getLogger(MailAction.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void forward(Mail mail) {
//        currentMail = new Mail();
//        currentMail.setAttachement(mail.getAttachement());
//        currentMail.setBody(mailTranfer(mail));
//        currentMail.setSubject("FW : " + mail.getSubject());
//        return currentMail;
    }
    
    private String mailTranfer(Message message) throws MessagingException, IOException {
        return "\n\n\n------------------------\n"
                + "From : " + Arrays.toString(message.getFrom()) + "\n"
                + "To : " + Arrays.toString(message.getRecipients(Message.RecipientType.TO)) + "\n"
                + "Date : " + message.getReceivedDate() + "\n\n"
                + ReadMailAction.getContentMessage(message)
                + "\n\n";
    }
}
