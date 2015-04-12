/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.sql.Date;
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
public class MailTest {
    
    public MailTest() {
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
     * Test of getId method, of class Mail.
     */
    @Test
    public void testGetId() {
	System.out.println("getId");
	Mail instance = new Mail();
	int expResult = 0;
	int result = instance.getId();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Mail.
     */
    @Test
    public void testSetId() {
	System.out.println("setId");
	int id = 0;
	Mail instance = new Mail();
	instance.setId(id);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class Mail.
     */
    @Test
    public void testGetDate() {
	System.out.println("getDate");
	Mail instance = new Mail();
	Date expResult = null;
	Date result = instance.getDate();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class Mail.
     */
    @Test
    public void testSetDate() {
	System.out.println("setDate");
	Date date = null;
	Mail instance = new Mail();
	instance.setDate(date);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getTo method, of class Mail.
     */
    @Test
    public void testGetTo() {
	System.out.println("getTo");
	Mail instance = new Mail();
	String expResult = "";
	String result = instance.getTo();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setTo method, of class Mail.
     */
    @Test
    public void testSetTo() {
	System.out.println("setTo");
	String to = "";
	Mail instance = new Mail();
	instance.setTo(to);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getFrom method, of class Mail.
     */
    @Test
    public void testGetFrom() {
	System.out.println("getFrom");
	Mail instance = new Mail();
	String expResult = "";
	String result = instance.getFrom();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setFrom method, of class Mail.
     */
    @Test
    public void testSetFrom() {
	System.out.println("setFrom");
	String from = "";
	Mail instance = new Mail();
	instance.setFrom(from);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getSubject method, of class Mail.
     */
    @Test
    public void testGetSubject() {
	System.out.println("getSubject");
	Mail instance = new Mail();
	String expResult = "";
	String result = instance.getSubject();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setSubject method, of class Mail.
     */
    @Test
    public void testSetSubject() {
	System.out.println("setSubject");
	String subject = "";
	Mail instance = new Mail();
	instance.setSubject(subject);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getPriority method, of class Mail.
     */
    @Test
    public void testGetPriority() {
	System.out.println("getPriority");
	Mail instance = new Mail();
	String expResult = "";
	String result = instance.getPriority();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setPriority method, of class Mail.
     */
    @Test
    public void testSetPriority() {
	System.out.println("setPriority");
	String priority = "";
	Mail instance = new Mail();
	instance.setPriority(priority);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getBody method, of class Mail.
     */
    @Test
    public void testGetBody() {
	System.out.println("getBody");
	Mail instance = new Mail();
	String expResult = "";
	String result = instance.getBody();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setBody method, of class Mail.
     */
    @Test
    public void testSetBody() {
	System.out.println("setBody");
	String body = "";
	Mail instance = new Mail();
	instance.setBody(body);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getAttachement method, of class Mail.
     */
    @Test
    public void testGetAttachement() {
	System.out.println("getAttachement");
	Mail instance = new Mail();
	File[] expResult = null;
	File[] result = instance.getAttachement();
	assertArrayEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setAttachement method, of class Mail.
     */
    @Test
    public void testSetAttachement() {
	System.out.println("setAttachement");
	File[] attachement = null;
	Mail instance = new Mail();
	instance.setAttachement(attachement);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Mail.
     */
    @Test
    public void testHashCode() {
	System.out.println("hashCode");
	Mail instance = new Mail();
	int expResult = 0;
	int result = instance.hashCode();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Mail.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");
	Object obj = null;
	Mail instance = new Mail();
	boolean expResult = false;
	boolean result = instance.equals(obj);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
