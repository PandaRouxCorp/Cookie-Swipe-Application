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
import org.junit.ClassRule;
import org.junit.Rule;

/**
 *
 * @author mickx
 */
public class DeliverySystemTest {
    
    public DeliverySystemTest() {
    }
    
    public static Method isActived;
    public static Method hardStop;
    public static Method safeStop;
    public static Method addTask;
    public static Method launchListener;
    public static AbstractSender sender;

    @BeforeClass
    public static void setUpClass() {
        try {
            isActived = DeliverySystem.class.getDeclaredMethod("isActived");
            hardStop = DeliverySystem.class.getDeclaredMethod("hardStop");
            safeStop = DeliverySystem.class.getDeclaredMethod("safeStop");
            addTask = DeliverySystem.class.getDeclaredMethod("addTask", Message.class);
            launchListener = DeliverySystem.class.getDeclaredMethod("launchListener");
            
            addTask.setAccessible(true);
            isActived.setAccessible(true);
            launchListener.setAccessible(true);
            safeStop.setAccessible(true);
            hardStop.setAccessible(true);
            
            sender = new AbstractSender() {
                @Override
                public void onMessageReceived(Future receivedMessage) {
                    
                }
            };
            
            Postman.registerSender(sender);
            
        } catch (IllegalArgumentException
                |SecurityException
                |NoSuchMethodException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
        try {
            Field field = Postman.class.getDeclaredField("INSTANCE");
            field.setAccessible(true);
            Postman postman = (Postman) field.get(null);
            postman = null;
        } catch (IllegalArgumentException
                |NoSuchFieldException
                |SecurityException
                |IllegalAccessException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DeliverySystem deliverySystem;
    public ConcurrentLinkedQueue<Future<?>> futures;
    
    @Before
    public void setUp() {
        try {    
            Constructor<DeliverySystem> constructor;
            constructor = DeliverySystem.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            deliverySystem = constructor.newInstance();
            
            Field field = DeliverySystem.class.getDeclaredField("futures");
            field.setAccessible(true);
            futures = (ConcurrentLinkedQueue<Future<?>>) field.get(deliverySystem);        
        } catch (IllegalAccessException
                |IllegalArgumentException
                |InvocationTargetException
                |NoSuchFieldException
                |SecurityException
                |NoSuchMethodException
                |InstantiationException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of stop method, of class DeliverySystem.
     */
    @Test
    public void testStop() {
        try {
            
            System.out.println("stop");
            
            launchListener.invoke(deliverySystem);
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            assertTrue("RequestLauncher should be launched", (boolean)isActived.invoke(deliverySystem));
            
            safeStop.invoke(deliverySystem);
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            assertTrue("RequestLauncher should be stoped", !(boolean)isActived.invoke(deliverySystem));
        
        } catch (IllegalAccessException 
                |IllegalArgumentException 
                |InvocationTargetException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of kill method, of class DeliverySystem.
     */
    @Test
    public void testKill() {
        try {
            
            System.out.println("kill");
            
            launchListener.invoke(deliverySystem);
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            assertTrue("RequestLauncher should be launched", (boolean)isActived.invoke(deliverySystem));
            
            hardStop.invoke(deliverySystem);
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            assertTrue("RequestLauncher should be stoped", !(boolean)isActived.invoke(deliverySystem));
        
        } catch (IllegalAccessException 
                |IllegalArgumentException 
                |InvocationTargetException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of launch method, of class DeliverySystem.
     */
    @Test
    public void testLaunch() {
        System.out.println("launch");
        try {
            
            Message<Integer> message = new Message<Integer>(1) {
            
                @Override
                public Integer call() throws Exception {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            };
            
            DeliverySystem.launch(message);            
            DeliverySystem.kill();

            assertTrue("RequestLauncher should be launched", DeliverySystem.isLaunched());
            
        } catch (SecurityException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test 
    public void consomationTest() {
        System.out.println("consomationTest");
        try {
            
            int messageDuration = 100;
            
            List<Message<Integer>> messages = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                messages.add(new Message<Integer>(sender.getSenderId()) {
                    @Override
                    public Integer call() throws Exception {
                        Thread.sleep(messageDuration);
                        return getSender();
                    }
                });
            }
            
            assertTrue("Il n'y a aucun future en début de test " + futures.size(), futures.isEmpty());
            
            for(Message<?> m : messages) {
                addTask.invoke(deliverySystem, m);
            }
            
            assertTrue(messages.size() + "future(s) ajoutés", futures.size() == messages.size());
            
            launchListener.invoke(deliverySystem);
            
            Thread.sleep((messageDuration + 1)*messages.size()/2);
            
            assertTrue(messages.size() + "future(s) ajoutés", futures.isEmpty());
            
        } catch (IllegalAccessException
                |IllegalArgumentException
                |InvocationTargetException
                |SecurityException
                |InterruptedException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
