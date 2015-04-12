/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class UserTest {
    
    public UserTest() {
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
     * Test of create method, of class User.
     */
    @Test
    public void testCreate() {
	System.out.println("create");
	User instance = new User();
	User expResult = null;
	User result = instance.create();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class User.
     */
    @Test
    public void testConnect() {
	System.out.println("connect");
	User instance = new User();
	User expResult = null;
	User result = instance.connect();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class User.
     */
    @Test
    public void testGetData() {
	System.out.println("getData");
	User instance = new User();
	HashMap<String, Object> expResult = null;
	HashMap<String, Object> result = instance.getData();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of updateData method, of class User.
     */
    @Test
    public void testUpdateData() {
	System.out.println("updateData");
	HashMap<String, Object> data = null;
	User instance = new User();
	boolean expResult = false;
	boolean result = instance.updateData(data);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of addNewMailAccount method, of class User.
     */
    @Test
    public void testAddNewMailAccount() {
	System.out.println("addNewMailAccount");
	MailAccount newMailAccount = null;
	User instance = new User();
	boolean expResult = false;
	boolean result = instance.addNewMailAccount(newMailAccount);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMailAccount method, of class User.
     */
    @Test
    public void testDeleteMailAccount() {
	System.out.println("deleteMailAccount");
	MailAccount deletedMailAccount = null;
	User instance = new User();
	boolean expResult = false;
	boolean result = instance.deleteMailAccount(deletedMailAccount);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of blackListSender method, of class User.
     */
    @Test
    public void testBlackListSender() {
	System.out.println("blackListSender");
	String sender = "";
	User instance = new User();
	boolean expResult = false;
	boolean result = instance.blackListSender(sender);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of removeBlackListSender method, of class User.
     */
    @Test
    public void testRemoveBlackListSender() {
	System.out.println("removeBlackListSender");
	String sender = "";
	User instance = new User();
	boolean expResult = false;
	boolean result = instance.removeBlackListSender(sender);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class User.
     */
    @Test
    public void testGetId() {
	System.out.println("getId");
	User instance = new User();
	int expResult = 0;
	int result = instance.getId();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class User.
     */
    @Test
    public void testSetId() {
	System.out.println("setId");
	int id = 0;
	User instance = new User();
	instance.setId(id);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getLoginAdressMail method, of class User.
     */
    @Test
    public void testGetLoginAdressMail() {
	System.out.println("getLoginAdressMail");
	User instance = new User();
	String expResult = "";
	String result = instance.getLoginAdressMail();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setLoginAdressMail method, of class User.
     */
    @Test
    public void testSetLoginAdressMail() {
	System.out.println("setLoginAdressMail");
	String loginAdressMail = "";
	User instance = new User();
	instance.setLoginAdressMail(loginAdressMail);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
	System.out.println("getPassword");
	User instance = new User();
	String expResult = "";
	String result = instance.getPassword();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
	System.out.println("setPassword");
	String password = "";
	User instance = new User();
	instance.setPassword(password);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getBackupMail method, of class User.
     */
    @Test
    public void testGetBackupMail() {
	System.out.println("getBackupMail");
	User instance = new User();
	String expResult = "";
	String result = instance.getBackupMail();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setBackupMail method, of class User.
     */
    @Test
    public void testSetBackupMail() {
	System.out.println("setBackupMail");
	String backupMail = "";
	User instance = new User();
	instance.setBackupMail(backupMail);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getListOfMailAccount method, of class User.
     */
    @Test
    public void testGetListOfMailAccount() {
	System.out.println("getListOfMailAccount");
	User instance = new User();
	ArrayList<MailAccount> expResult = null;
	ArrayList<MailAccount> result = instance.getListOfMailAccount();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setListOfMailAccount method, of class User.
     */
    @Test
    public void testSetListOfMailAccount() {
	System.out.println("setListOfMailAccount");
	ArrayList<MailAccount> listOfMailAccount = null;
	User instance = new User();
	instance.setListOfMailAccount(listOfMailAccount);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getBlackList method, of class User.
     */
    @Test
    public void testGetBlackList() {
	System.out.println("getBlackList");
	User instance = new User();
	ArrayList<String> expResult = null;
	ArrayList<String> result = instance.getBlackList();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of setBlackList method, of class User.
     */
    @Test
    public void testSetBlackList() {
	System.out.println("setBlackList");
	ArrayList<String> blackList = null;
	User instance = new User();
	instance.setBlackList(blackList);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
	System.out.println("hashCode");
	User instance = new User();
	int expResult = 0;
	int result = instance.hashCode();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");
	Object obj = null;
	User instance = new User();
	boolean expResult = false;
	boolean result = instance.equals(obj);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
