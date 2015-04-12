/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class DispatcherTest {
    
    public DispatcherTest() {
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
     * Test of getListener method, of class Dispatcher.
     */
    @Test
    public void testGetListener() {
	System.out.println("getListener");
	Dispatcher instance = new Dispatcher();
	ActionListener expResult = null;
	ActionListener result = instance.getListener();
	assertEquals(expResult, result);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of sendAction method, of class Dispatcher.
     */
    @Test
    public void testSendAction() {
	System.out.println("sendAction");
	ActionEvent e = null;
	Dispatcher instance = new Dispatcher();
	instance.sendAction(e);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
