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
	System.out.println("registerSender");
	AbstractSender sender = null;
	Postman.registerSender(sender);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of unregisterSender method, of class Postman.
     */
    @Test
    public void testUnregisterSender() {
	System.out.println("unregisterSender");
	AbstractSender sender = null;
	Postman.unregisterSender(sender);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class Postman.
     */
    @Test
    public void testSendMessage() {
	System.out.println("sendMessage");
	Message<Object> message = null;
	Postman.sendMessage(message);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of sendResponse method, of class Postman.
     */
    @Test
    public void testSendResponse() {
	System.out.println("sendResponse");
	String senderID = "";
	Future<Object> response = null;
	Postman.sendResponse(senderID, response);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
