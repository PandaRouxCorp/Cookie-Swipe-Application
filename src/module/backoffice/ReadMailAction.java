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
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import model.MailAccount;
import module.ihm.ReadMailFrameInitializer;
import org.jsoup.Jsoup;
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
            new ReadMailFrameInitializer(frame).execute();
            // set frame
            frame.setCookieSwipeTextFieldObject(message.getSubject());
            frame.setCookieSwipeTextFieldTo(Arrays.toString(message.getFrom()));

            Object mess = message.getContent();
            String content = getContentMessage(message);
            
            frame.setjTextAreaMail(content);

            // then focus it
            application.setFocusFrame(frame);
            return true;
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(ReadMailAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static String getContentMessage(Message message) throws IOException, MessagingException {
        
        Object mess = message.getContent();
        String content = "";
        if (message.isMimeType("text/plain")) {
            content = (String) message.getContent();
        } else if (mess instanceof Multipart) {
            content = getContentMessageFormMultipart(message);
        } else if (message.isMimeType("message/rfc822")) {
            content = "message/rfc822 to do";
        } else {
            content = "others to do";
        }
        return content;
    }
    
    public static String getContentMessageFormMultipart(Message message) throws IOException, MessagingException {

        Object mess = message.getContent();
        String content = "";

        Multipart multipart = (Multipart) mess;
        for (int i = 0; i < multipart.getCount(); i++) {

            BodyPart bodyPart = multipart.getBodyPart(i);

            String disposition = bodyPart.getDisposition();

            if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                System.out.println("Mail have some attachment");

                DataHandler handler = bodyPart.getDataHandler();
                System.out.println("file name : " + handler.getName());
            } else {
                //Parser parse;
                content = getText(bodyPart);
                if (content.contains("<br/>")) {
                    content = content.replaceAll("<br/>", "\n");
                }
                if (content.contains("<br>")) {
                    content = content.replaceAll("<br>", "\n");
                }
                content = html2text(content);
            }
        }
        return content;
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    public static String getText(Part p) throws MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String) p.getContent();
            boolean textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart) p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null) {
                        text = getText(bp);
                    }
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null) {
                        return s;
                    }
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null) {
                    return s;
                }
            }
        }
        return null;
    }

}
