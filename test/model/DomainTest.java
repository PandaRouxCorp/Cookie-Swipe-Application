/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
public class DomainTest {
    
    public DomainTest() {
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
     * Test of sendMail method, of class Domain.
     */
    @Test
    public void testSendMail() {
	System.out.println("sendMail");
	Mail mail = null;
	Domain instance = new Domain();
	boolean expResult = false;
	boolean result = instance.sendMail(mail);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getDomain method, of class Domain.
     */
    @Test
    public void testGetDomain() {
	System.out.println("getDomain");
	Domain instance = new Domain();
	String expResult = "";
	String result = instance.getDomain();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getPopAddress method, of class Domain.
     */
    @Test
    public void testGetPopAddress() {
	System.out.println("getPopAddress");
	Domain instance = new Domain();
	String expResult = "";
	String result = instance.getPopAddress();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getSmtpAddress method, of class Domain.
     */
    @Test
    public void testGetSmtpAddress() {
	System.out.println("getSmtpAddress");
	Domain instance = new Domain();
	String expResult = "";
	String result = instance.getSmtpAddress();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of getPort method, of class Domain.
     */
    @Test
    public void testGetPort() {
	System.out.println("getPort");
	Domain instance = new Domain();
	String expResult = "";
	String result = instance.getPort();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Domain.
     */
    @Test
    public void testHashCode() {
	System.out.println("hashCode");
	Domain instance = new Domain();
	int expResult = 0;
	int result = instance.hashCode();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Domain.
     */
    @Test
    public void testEquals() {
	System.out.println("equals");
	Object obj = null;
	Domain instance = new Domain();
	boolean expResult = false;
	boolean result = instance.equals(obj);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
