/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import cookie.swipe.application.CookieSwipeApplication;
import cookie.swipe.application.SystemSettings;
import interfaces.IAction;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import module.ihm.ReadMailFrameInitializer;
import network.messageFramework.AbstractSender;
import network.messageFramework.FrameworkMessage;
import org.jsoup.Jsoup;
import view.ReadMailCSFrame;
import view.component.CookieSwipeTextArea;

/**
 * @author Yehouda
 */
public class ReadMailAction implements IAction {
    
    private static ReadMailCSFrame frame;
    
    @Override
    public boolean execute(Object... object) {
        try {
            Message message = (Message) object[0];
            CookieSwipeApplication application = CookieSwipeApplication.getApplication();
            
            frame = new ReadMailCSFrame();
            new ReadMailFrameInitializer(frame).execute();
            // set frame
            frame.setCookieSwipeTextFieldObject(message.getSubject());
            frame.setCookieSwipeTextFieldTo(Arrays.toString(message.getFrom()));

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
        String content;
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

            } else if (bodyPart.getContentType().contains("image/")) {
                System.out.println("content type" + bodyPart.getContentType());

                File f = new File("image" + new Date().getTime() + ".jpg");
                byte[] buffer;
                try (DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f))); 
                     com.sun.mail.util.BASE64DecoderStream test = (com.sun.mail.util.BASE64DecoderStream) bodyPart.getContent()) {
                    buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = test.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }                  
                }
                CookieSwipeTextArea area = frame.getjTextAreaMail();
                if (area != null) {
                    ImagePanel img = new ImagePanel(Arrays.toString(buffer));
                    img.paintComponent(area.getGraphics());
                }
            } else {
                content = getText(bodyPart);
                if(bodyPart.isMimeType("text/html")) {
                    if (content.contains("<br/>")) {
                        content = content.replaceAll("<br/>", "\n");
                    }
                    if (content.contains("<br>")) {
                        content = content.replaceAll("<br>", "\n");
                    }

                    if (content.contains("<img")) { // telecharge toutes les image du mail
                        List<String> urls = getUrls(content);
                        for (final String src : urls) {

                            AbstractSender<Boolean> s = new AbstractSender<Boolean>() {

                                @Override
                                public void onMessageReceived(Future<Boolean> receivedMessage) {
                                    try {
                                        boolean b = receivedMessage.get();
                                        if (b) {
                                            CookieSwipeTextArea area = frame.getjTextAreaMail();
                                            area.repaint();
                                            area.revalidate();
                                        } else {

                                            JOptionPane.showMessageDialog(null, "une image n'a pas été téléchargé",
                                                    "Telechargement des images", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } catch (InterruptedException | ExecutionException ex) {
                                        Logger.getLogger(ReadMailAction.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                            };

                            s.sendMessage(new FrameworkMessage< Boolean>() {

                                @Override
                                public Boolean call() throws Exception {

                                    File file = downloadImage(src, "tmp/img/");
                                    return file != null;
                                }

                            });
                        }
                    }
                }
                if(content != null)
                    content = html2text(content);
            }
        }
        return content;
    }
    
    private static List<String> getUrls(String html) {
        List<String> urls = new ArrayList<>();
        int start, end = 0;
        String refStart = "src=\"",
               refEnd   = "\"";
        while( (start = html.indexOf(refStart, end)) != -1 ) {
            start += refStart.length();
            end = html.indexOf(refEnd, start);
            String a = html.substring(start, end);
            System.out.println(a);
            if ( a != null && !a.isEmpty() && !(a.contains("jpg") || a.contains("png")) )
                if(a.contains("jpg") && !a.endsWith("jpg"))
                    a = a.substring(0, a.indexOf(".jpg") + 4);
                if(a.contains("png") && !a.endsWith("png"))
                    a = a.substring(0, a.lastIndexOf(".png") + 4);
                urls.add(a);
        }
        return urls;
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    public static File downloadImage(String src) {
        return downloadImage(src, "tmp/img/");
    }
    
    public static File downloadImage(String src, String path) {
        OutputStream os = null;
        try {
            URL url = new URL(src);
            URLConnection conn = url.openConnection();
            File file;
            try (InputStream in = conn.getInputStream()) {
                File f = new File(SystemSettings.PATH_HOME + path);
                if (!f.exists()) {
                    f.mkdirs();
                }
                String image = SystemSettings.PATH_HOME + path + src.substring(src.lastIndexOf("/"));
                file = new File(image);
                if (file.exists()) {
                    return null;
                }
                os = new FileOutputStream(image);
                byte[] b = new byte[2048];
                int length;
                while ((length = in.read(b)) != -1) {
                    os.write(b, 0, length);
                }
            }
            return file;
        }   catch (MalformedURLException ex) {
            Logger.getLogger(ReadMailAction.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (IOException ex) {
            Logger.getLogger(ReadMailAction.class.getName()).log(Level.SEVERE, null, ex);
        }   finally {
            try {
                if(os != null) os.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadMailAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
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
    
    public static class ImagePanel extends JPanel {

        private BufferedImage image;

        public ImagePanel(String file) throws IOException {
            image = ImageIO.read(new File(file));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
    }

}
