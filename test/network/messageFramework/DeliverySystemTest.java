/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mickx
 */
public class DeliverySystemTest {
    
    public DeliverySystemTest() {
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
     * Test of stop method, of class DeliverySystem.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        DeliverySystem.stop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue("RequestLauncher should be stoped", !DeliverySystem.isLaunched());
    }

    /**
     * Test of kill method, of class DeliverySystem.
     */
    @Test
    public void testKill() {
        System.out.println("kill");
        DeliverySystem.kill();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue("RequestLauncher should be stoped", !DeliverySystem.isLaunched());
    }

    /**
     * Test of launch method, of class DeliverySystem.
     */
    @Test
    public void testLaunch() {
        System.out.println("launch");
        Message<Integer> message = new Message<Integer>(1) {
            
            @Override
            public Integer call() throws Exception {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        DeliverySystem.launch(message);
        assertTrue("RequestLauncher should be launched", DeliverySystem.isLaunched());
    }
    
    @Test 
    public void consomationTest() {
        try {
            
            int messageDuration = 100;
            
            List<Message<Integer>> messages = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                messages.add(new Message<Integer>(i) {
                    @Override
                    public Integer call() throws Exception {
                        Thread.sleep(messageDuration);
                        return getSender();
                    }
                });
            }
            
            Method launchListener = DeliverySystem.class.getDeclaredMethod("launchListener");
            launchListener.setAccessible(true);
            
            Method addTask = DeliverySystem.class.getDeclaredMethod("addTask", Message.class);
            addTask.setAccessible(true);
            
            Constructor<DeliverySystem> constructor = DeliverySystem.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object launcher = constructor.newInstance();
            
            Field field = DeliverySystem.class.getDeclaredField("futures");
            field.setAccessible(true);
            ConcurrentLinkedQueue<Future<?>> futures = (ConcurrentLinkedQueue<Future<?>>) field.get(launcher);
            
            assertTrue("Il n'y a aucun future en début de test " + futures.size(), futures.isEmpty());
            
            for(Message<?> m : messages) {
                addTask.invoke(launcher, m);
            }
            
            assertTrue(messages.size() + "future(s) ajoutés", futures.size() == messages.size());
            
            launchListener.invoke(launcher);
            
            Thread.sleep((messageDuration + 1)*messages.size());
            
            assertTrue(messages.size() + "future(s) ajoutés", futures.isEmpty());
            
        } catch (IllegalAccessException
                |IllegalArgumentException
                |InvocationTargetException
                |NoSuchFieldException
                |SecurityException
                |NoSuchMethodException
                |InterruptedException
                |InstantiationException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
