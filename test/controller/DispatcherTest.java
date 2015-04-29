/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.IJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.LoginJFrame;
import view.MailAccountJFrame;
import view.MainJFrame;

/**
 *
 * @author Suiken
 */
public class DispatcherTest {
    
    static Dispatcher dispatcher;
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
        dispatcher = Dispatcher.getInstance();
    }
    
    @After
    public void tearDown() {
        dispatcher = null;
    }

    @Test
    public void testGetInstance(){
        dispatcher = null;
        assertNull(dispatcher);
        dispatcher = Dispatcher.getInstance();
        assertNotNull(dispatcher);
        assertEquals(dispatcher, Dispatcher.getInstance());
        assertNotNull(dispatcher.getMainFrame());
        assertEquals(dispatcher.getMainFrame().getClass(),LoginJFrame.class);
        assertEquals(dispatcher.getFocusFrame(), null);
        //TODO
        //S'occuper de la variable user
    }
    
    /**
     * Test of getListener method, of class Dispatcher.
     */
    @Test
    public void testGetListener() {
	//FIXME
        /*System.out.println("getListener");
        java.awt.event.ActionListener al = dispatcher.getListener();
        System.out.println(al.toString());
        assertEquals(java.awt.event.ActionListener.class, al.toString());*/
    }

    /**
     * Test of sendAction method, of class Dispatcher.
     */
    @Test
    public void testSendAction() {
        assertEquals(null, dispatcher.getFocusFrame());
	
        ActionEvent addMailAccount = new ActionEvent(new JButton(ActionName.addMailAccount), 1, ActionName.addMailAccount);
	dispatcher.sendAction(addMailAccount);
        assertEquals(MailAccountJFrame.class, dispatcher.getFocusFrame().getClass());
        
        ActionEvent udpateMailAccount = new ActionEvent(new JButton(ActionName.udpateMailAccount), 1, ActionName.udpateMailAccount);
	dispatcher.sendAction(udpateMailAccount);
        assertEquals(MailAccountJFrame.class, dispatcher.getFocusFrame().getClass());
        dispatcher.setFocusFrame(null);
        
        ActionEvent nullTest = new ActionEvent(new JButton("nullTest"), 1, "nullTest");
	dispatcher.sendAction(nullTest);
        assertNull(dispatcher.getFocusFrame());
        
        ActionEvent createMailAccount = new ActionEvent(new JButton(ActionName.createMailAccount), 1, ActionName.createMailAccount);
	dispatcher.sendAction(createMailAccount);
        assertNotNull(dispatcher.getUser());
        assertNotNull(nullTest);
        
        
        
        
	// TODO review the generated test code and remove the default call to fail.
	//assertEquals(, "TestSendAction");
        //fail("The test case is a prototype.");
    }
    
}
