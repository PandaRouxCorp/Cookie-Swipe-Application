/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import interfaces.IAction;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import model.MailAccount;
import view.ReadMailCSFrame;

/**
 * @author Yehouda
 */
public class ReadMailAction implements IAction {

    @Override
    public boolean execute(Object... object) {
        try {
            Message message = (Message) object[0];
            CookieSwipeApplication application = CookieSwipeApplication.getApplication();
            MailAccount mailAccount = (MailAccount) CookieSwipeApplication.getApplication().getParam("mailAccountSelected");
            
            ReadMailCSFrame frame = new ReadMailCSFrame();
            // set frame
            frame.setCookieSwipeTextFieldObject(message.getSubject());
            frame.setCookieSwipeTextFieldTo(Arrays.toString(message.getFrom()));
            
            Object mess = message.getContent();
            String content = "";
            
            if (message.isMimeType("text/plain")) {
                frame.setjTextAreaMail((String) message.getContent());
            } else if (mess instanceof Multipart) {
                
                Multipart multipart = (Multipart) mess;
                for (int i = 0; i < multipart.getCount(); i++) {

                    BodyPart bodyPart = multipart.getBodyPart(i);
                    
                    String disposition = bodyPart.getDisposition();
                    
                    if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                        System.out.println("Mail have some attachment");

                        DataHandler handler = bodyPart.getDataHandler();
                        System.out.println("file name : " + handler.getName());
                    } else {
                        content = getText(bodyPart);
                    }
                }

                frame.setjTextAreaMail(content);
                
            } else if (message.isMimeType("message/rfc822")) {
                frame.setjTextAreaMail("message/rfc822 to do");
            } else {
                frame.setjTextAreaMail("others to do");
            }

            // then focus it
            application.setFocusFrame(frame);
            return true;
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(ReadMailAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private String getText(Part p) throws MessagingException, IOException {
       if (p.isMimeType("text/*")) {
           String s = (String)p.getContent();
           boolean textIsHtml = p.isMimeType("text/html");
           return s;
       }
 
       if (p.isMimeType("multipart/alternative")) {
           // prefer html text over plain text
           Multipart mp = (Multipart)p.getContent();
           String text = null;
           for (int i = 0; i < mp.getCount(); i++) {
               Part bp = mp.getBodyPart(i);
               if (bp.isMimeType("text/plain")) {
                   if (text == null)
                       text = getText(bp);
                   continue;
               } else if (bp.isMimeType("text/html")) {
                   String s = getText(bp);
                   if (s != null)
                       return s;
               } else {
                   return getText(bp);
               }
           }
           return text;
       } else if (p.isMimeType("multipart/*")) {
           Multipart mp = (Multipart)p.getContent();
           for (int i = 0; i < mp.getCount(); i++) {
               String s = getText(mp.getBodyPart(i));
               if (s != null)
                   return s;
           }
       }
       return null;
   }
    
}
