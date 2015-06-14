/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
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
public class MailAccountTest {
    
    public MailAccountTest() {
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
    
    @Test
    public void sendMail() {
    	Mail mail = new Mail();
    	mail.setSubject("c'est une test");
    	mail.setBody("le body du test");
//    	panda.roux.corp@gmail.com
//    	mdp: panda
    	MailAccount account = new MailAccount();
    	account.sendMail(mail);
    }

    /**
     * Test of getData method, of class MailAccount.
     */
    @Test
    public void testGetData() {
	System.out.println("getData");
	MailAccount instance = new MailAccount();
	HashMap<String, Object> expResult = null;
	HashMap<String, Object> result = instance.getData();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of updateData method, of class MailAccount.
     */
    @Test
    public void testUpdateData() {
	System.out.println("updateData");
	HashMap<String, Object> data = null;
	MailAccount instance = new MailAccount();
	boolean expResult = false;
	boolean result = instance.updateData(data);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of createNewMail method, of class MailAccount.
     */
    @Test
    public void testCreateNewMail() {
	System.out.println("createNewMail");
	MailAccount instance = new MailAccount();
	Mail expResult = null;
	Mail result = instance.createNewMail();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of sendMail method, of class MailAccount.
     */
    @Test
    public void testSendMail() {
	System.out.println("sendMail");
	Mail mail = null;
	MailAccount instance = new MailAccount();
	boolean expResult = false;
	boolean result = instance.sendMail(mail);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMail method, of class MailAccount.
     */
    @Test
    public void testDeleteMail() {
	System.out.println("deleteMail");
	Mail mail = null;
	MailAccount instance = new MailAccount();
	boolean expResult = false;
	boolean result = instance.deleteMail(mail);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class MailAccount.
     */
    @Test
    public void testGetId() {
	System.out.println("getId");
	MailAccount instance = new MailAccount();
	int expResult = 0;
	int result = instance.getId();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class MailAccount.
     */
    @Test
    public void testSetId() {
	System.out.println("setId");
	int id = 0;
	MailAccount instance = new MailAccount();
	instance.setId(id);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getAddress method, of class MailAccount.
     */
    @Test
    public void testGetAddress() {
	System.out.println("getAddress");
	MailAccount instance = new MailAccount();
	String expResult = "";
	String result = instance.getAddress();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setAddress method, of class MailAccount.
     */
    @Test
    public void testSetAddress() {
	System.out.println("setAddress");
	String address = "";
	MailAccount instance = new MailAccount();
	instance.setAddress(address);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getCSName method, of class MailAccount.
     */
    @Test
    public void testGetCSName() {
	System.out.println("getCSName");
	MailAccount instance = new MailAccount();
	String expResult = "";
	String result = instance.getCSName();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setCSName method, of class MailAccount.
     */
    @Test
    public void testSetCSName() {
	System.out.println("setCSName");
	String CSName = "";
	MailAccount instance = new MailAccount();
	instance.setCSName(CSName);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getDomain method, of class MailAccount.
     */
    @Test
    public void testGetDomain() {
	System.out.println("getDomain");
	MailAccount instance = new MailAccount();
	Domain expResult = null;
	Domain result = instance.getDomain();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setDomain method, of class MailAccount.
     */
    @Test
    public void testSetDomain() {
	System.out.println("setDomain");
	Domain domain = null;
	MailAccount instance = new MailAccount();
	instance.setDomain(domain);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class MailAccount.
     */
    @Test
    public void testGetPassword() {
	System.out.println("getPassword");
	MailAccount instance = new MailAccount();
	String expResult = "";
	String result = instance.getPassword();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class MailAccount.
     */
    @Test
    public void testSetPassword() {
	System.out.println("setPassword");
	String password = "";
	MailAccount instance = new MailAccount();
	instance.setPassword(password);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getMailSignature method, of class MailAccount.
     */
    @Test
    public void testGetMailSignature() {
	System.out.println("getMailSignature");
	MailAccount instance = new MailAccount();
	String expResult = "";
	String result = instance.getMailSignature();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setMailSignature method, of class MailAccount.
     */
    @Test
    public void testSetMailSignature() {
	System.out.println("setMailSignature");
	String mailSignature = "";
	MailAccount instance = new MailAccount();
	instance.setMailSignature(mailSignature);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class MailAccount.
     */
    @Test
    public void testGetColor() {
	System.out.println("getColor");
	MailAccount instance = new MailAccount();
	String expResult = "";
	String result = instance.getColor();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class MailAccount.
     */
    @Test
    public void testSetColor() {
	System.out.println("setColor");
	String color = "";
	MailAccount instance = new MailAccount();
	instance.setColor(color);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getLastSynch method, of class MailAccount.
     */
    @Test
    public void testGetLastSynch() {
	System.out.println("getLastSynch");
	MailAccount instance = new MailAccount();
	Date expResult = null;
	Date result = instance.getLastSynch();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setLastSynch method, of class MailAccount.
     */
    @Test
    public void testSetLastSynch() {
	System.out.println("setLastSynch");
	Date lastSynch = null;
	MailAccount instance = new MailAccount();
	instance.setLastSynch(lastSynch);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getListOfmail method, of class MailAccount.
     */
    @Test
    public void testGetListOfmail() {
	System.out.println("getListOfmail");
	MailAccount instance = new MailAccount();
	ArrayList<Mail> expResult = null;
	ArrayList<Mail> result = instance.getListOfmail();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setListOfmail method, of class MailAccount.
     */
    @Test
    public void testSetListOfmail() {
	System.out.println("setListOfmail");
	ArrayList<Mail> listOfmail = null;
	MailAccount instance = new MailAccount();
	instance.setListOfmail(listOfmail);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class MailAccount.
     */
    @Test
    public void testHashCode() {
	System.out.println("hashCode");
	MailAccount instance = new MailAccount();
	int expResult = 0;
	int result = instance.hashCode();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class MailAccount.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");
	Object obj = null;
	MailAccount instance = new MailAccount();
	boolean expResult = false;
	boolean result = instance.equals(obj);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
