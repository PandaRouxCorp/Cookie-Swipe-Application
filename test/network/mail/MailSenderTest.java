/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.mail;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mail;
import network.messageFramework.DeliverySystem;
import network.messageFramework.Postman;
import static network.messageFramework.Postman.COOKIE_SWIPE_DIR;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mickx
 */
public class MailSenderTest {
    
    public MailSenderTest() {
    }
    
    public static Field instanceField;
    public static Field mails;
    public static Method add;
    public static Constructor mailSenderConstructor;
    public static Constructor deliveryConstructor;
    
    @BeforeClass
    public static void setUpClass() {
        try {
            instanceField = MailSender.class.getDeclaredField("INSTANCE");
            add = MailSender.class.getDeclaredMethod("add", Mail.class);
            mails = MailSender.class.getDeclaredField("mails");
            mailSenderConstructor = MailSender.class.getDeclaredConstructor();
            //deliveryConstructor = DeliverySystem.class
        } catch (NoSuchFieldException | SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(MailSenderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instanceField.setAccessible(true);
        add.setAccessible(true);
        mails.setAccessible(true);
        mailSenderConstructor.setAccessible(true);
    }
    
    @After
    public void tearDown() {
        File f = new File(COOKIE_SWIPE_DIR);
        if(f.exists()) f.delete();
    }
    
    @Test // send one mail and receive the acknowledment
    public void simpleCommunicationTest() {
        try {
            System.out.println("Simple communication test");
            Mail m = new Mail();
            MailSender.send(m);
            MailSender mailSender = (MailSender) instanceField.get(null);
            ConcurrentLinkedQueue<Mail> mailsList = (ConcurrentLinkedQueue<Mail>) mails.get(mailSender);
            assertTrue("Mail is being sent", mailsList.contains(m));
            Thread.sleep(1000);
            assertTrue("Mail have been sent " + mailsList.size(), !mailsList.contains(m));
        } catch (IllegalArgumentException | IllegalAccessException | InterruptedException ex) {
            Logger.getLogger(MailSenderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test // send mails, interrupt the communication, restart it and try to receive acknowledments
    public void interruptedCommunicationTest() {
        try {
            System.out.println("Interrupted communication test");
            Mail m = new Mail();
            MailSender.send(m);
            MailSender mailSender = (MailSender) instanceField.get(null);
            ConcurrentLinkedQueue<Mail> mailsList = (ConcurrentLinkedQueue<Mail>) mails.get(mailSender);
            assertTrue("Mail is being sent", mailsList.contains(m));
            DeliverySystem.stop();
            Thread.sleep(500);
            assertTrue("DeliverySystem is down", !DeliverySystem.isLaunched());
            
            instanceField.set(null, mailSenderConstructor.newInstance());
            mailSender = (MailSender) instanceField.get(null);
            mailsList = (ConcurrentLinkedQueue<Mail>) mails.get(mailSender);
            assertTrue("No mail", mailsList.isEmpty());
            
            Postman.sendMessage(null);
//            assertTrue("Have mail", !mailsList.isEmpty());
            Thread.sleep(3000);
            assertTrue("Mail have been sent " + mailsList.size(), !mailsList.contains(m));
        } catch (IllegalArgumentException | IllegalAccessException | InterruptedException
                | InstantiationException | InvocationTargetException ex) {
            Logger.getLogger(MailSenderTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
