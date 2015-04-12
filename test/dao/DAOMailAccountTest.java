/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.MailAccount;
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
public class DAOMailAccountTest {
    
    public DAOMailAccountTest() {
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
     * Test of createMailAccount method, of class DAOMailAccount.
     */
    @Test
    public void testCreateMailAccount() {
	System.out.println("createMailAccount");
	MailAccount mailAccount = null;
	boolean expResult = false;
	boolean result = DAOMailAccount.createMailAccount(mailAccount);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of loadMailAccount method, of class DAOMailAccount.
     */
    @Test
    public void testLoadMailAccount() {
	System.out.println("loadMailAccount");
	MailAccount mailAccount = null;
	boolean expResult = false;
	boolean result = DAOMailAccount.loadMailAccount(mailAccount);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of loadDomail method, of class DAOMailAccount.
     */
    @Test
    public void testLoadDomail() {
	System.out.println("loadDomail");
	MailAccount mailAccount = null;
	boolean expResult = false;
	boolean result = DAOMailAccount.loadDomail(mailAccount);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of loadMail method, of class DAOMailAccount.
     */
    @Test
    public void testLoadMail() {
	System.out.println("loadMail");
	MailAccount mailAccount = null;
	boolean expResult = false;
	boolean result = DAOMailAccount.loadMail(mailAccount);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
