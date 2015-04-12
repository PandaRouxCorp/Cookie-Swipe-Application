/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;
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
public class DAOUserTest {
    
    public DAOUserTest() {
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
     * Test of createUser method, of class DAOUser.
     */
    @Test
    public void testCreateUser() {
	System.out.println("createUser");
	User user = null;
	boolean expResult = false;
	boolean result = DAOUser.createUser(user);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of connectUser method, of class DAOUser.
     */
    @Test
    public void testConnectUser() {
	System.out.println("connectUser");
	User user = null;
	boolean expResult = false;
	boolean result = DAOUser.connectUser(user);
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
