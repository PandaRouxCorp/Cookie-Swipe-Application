/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.util.concurrent.Future;
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
public class AbstractSenderTest {
    
    public AbstractSenderTest() {
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
     * Test of getSenderId method, of class AbstractSender.
     */
    @Test
    public void testGetSenderId() {
	System.out.println("getSenderId");
	AbstractSender instance = null;
	String expResult = "";
	String result = instance.getSenderId();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of onMessageReceived method, of class AbstractSender.
     */
    @Test
    public void testOnMessageReceived() {
	System.out.println("onMessageReceived");
	AbstractSender instance = null;
	instance.onMessageReceived(null);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of onGenericMessageReceived method, of class AbstractSender.
     */
    @Test
    public void testOnGenericMessageReceived() {
	System.out.println("onGenericMessageReceived");
	Future<Object> receivedMessage = null;
	AbstractSender instance = null;
	instance.onGenericMessageReceived(receivedMessage);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class AbstractSender.
     */
    @Test
    public void testSendMessage() {
	System.out.println("sendMessage");
	Message sentMessage = null;
	AbstractSender instance = null;
	instance.sendMessage(sentMessage);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    public class AbstractSenderImpl extends AbstractSender {

	public AbstractSenderImpl() {
	    super("");
	}

	public void onMessageReceived(Future<T> receivedMessage) {
	}
    }
    
}
