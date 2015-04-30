/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.util.concurrent.Future;

import network.mail.MailSender;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Suiken
 */
public class PostmanTest {
    
    public PostmanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerSender method, of class Postman.
     */
    @Test
    public void testRegisterSender() {
	System.out.println("Register and unregister senders");
	AbstractSender sender = new AbstractSender() {

        @Override
        public void onMessageReceived(Future receivedMessage) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
//	MailSender msender = new MailSender();
//  System.out.println(msender.getSenderId());
    
    System.out.println(sender.getSenderId());
	Postman.registerSender(sender);
        assertTrue("Sender is registered", Postman.isSenderRegistered(sender));
        Postman.unregisterSender(sender);
        assertTrue("Sender is registered", !Postman.isSenderRegistered(sender));
    }    
}
