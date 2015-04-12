/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

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
public class RequestLauncherTest {
    
    public RequestLauncherTest() {
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
     * Test of stop method, of class RequestLauncher.
     */
    @Test
    public void testStop() {
	System.out.println("stop");
	RequestLauncher.stop();
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of kill method, of class RequestLauncher.
     */
    @Test
    public void testKill() {
	System.out.println("kill");
	RequestLauncher.kill();
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    /**
     * Test of launch method, of class RequestLauncher.
     */
    @Test
    public void testLaunch() {
	System.out.println("launch");
	Message<Object> message = null;
	RequestLauncher.launch(message);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
    
}
