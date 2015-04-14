/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.mail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mail;
import network.messageFramework.DeliverySystem;
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
    
    @BeforeClass
    public static void setUpClass() {
        try {
            instanceField = MailSender.class.getDeclaredField("INSTANCE");
            add = MailSender.class.getDeclaredMethod("add", Mail.class);
            mails = MailSender.class.getDeclaredField("mails");
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
    }
    
    @After
    public void tearDown() {
    }

//    /**
//     * Test of onMessageReceived method, of class MailSender.
//     */
//    @Test
//    public void testOnMessageReceived() {
//        System.out.println("onMessageReceived");
//        Future<Mail> receivedMessage = null;
//        MailSender instance = null;
//        instance.onMessageReceived(receivedMessage);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of add method, of class MailSender.
//     */
//    @Test
//    public void testAdd() {
//        System.out.println("add");
//        Mail m = null;
//        MailSender instance = null;
//        instance.add(m);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of send method, of class MailSender.
//     */
//    @Test
//    public void testSend() {
//        System.out.println("send");
//        Mail m = null;
//        MailSender.send(m);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    @Test
    public void simpleCommunicationTest() {
        try {
            System.out.println("Simple communication test: send one mail and receive the acknowledment");
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
    
}
