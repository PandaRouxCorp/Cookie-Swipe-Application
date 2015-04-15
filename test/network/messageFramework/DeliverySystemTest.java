/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.io.File;
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
import static network.messageFramework.Postman.COOKIE_SWIPE_DIR;
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
    
    public static Method isActived;
    public static Method hardStop;
    public static Method safeStop;
    public static Method addTask;
    public static Method launchListener;
    public static Method saveState;
    public static Method retreiveState;
    public static TestSender sender;
    public static Field deliverySystemField;

    @BeforeClass
    public static void setUpClass() {
        try {
            isActived = DeliverySystem.class.getDeclaredMethod("isActived");
            hardStop = DeliverySystem.class.getDeclaredMethod("hardStop");
            safeStop = DeliverySystem.class.getDeclaredMethod("safeStop");
            addTask = DeliverySystem.class.getDeclaredMethod("addTask", Message.class);
            launchListener = DeliverySystem.class.getDeclaredMethod("launchListener");
            saveState = DeliverySystem.class.getDeclaredMethod("saveState");
            retreiveState = DeliverySystem.class.getDeclaredMethod("retreiveState");
            deliverySystemField = DeliverySystem.class.getDeclaredField("INSTANCE");
            
            addTask.setAccessible(true);
            isActived.setAccessible(true);
            launchListener.setAccessible(true);
            safeStop.setAccessible(true);
            hardStop.setAccessible(true);
            saveState.setAccessible(true);
            retreiveState.setAccessible(true);
            deliverySystemField.setAccessible(true);
            
            sender = TestSender.getSender();
            
            Postman.registerSender(sender);
            
            File f = new File(COOKIE_SWIPE_DIR);
            if(f.exists()) f.delete();
            
        } catch (IllegalArgumentException
                |SecurityException
                |NoSuchMethodException
                |NoSuchFieldException ex) {
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
            deliverySystem = (DeliverySystem) deliverySystemField.get(null);
            
            Field futuresfield = DeliverySystem.class.getDeclaredField("futures");
            futuresfield.setAccessible(true);
            futures = (ConcurrentLinkedQueue<Future<?>>) futuresfield.get(deliverySystem);        
        } catch (IllegalAccessException
                |IllegalArgumentException
                |NoSuchFieldException
                |SecurityException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        try {
            safeStop.invoke(deliverySystem);
            Thread.sleep(500);
            File f = new File(COOKIE_SWIPE_DIR);
            if(f.exists()) f.delete();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InterruptedException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of kill method, of class DeliverySystem.
     */
    @Test
    public void testKill() {
        try {
            
            System.out.println("kill test");
            
            launchListener.invoke(deliverySystem);
            
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            assertTrue("RequestLauncher should be launched", (boolean)isActived.invoke(deliverySystem));
            
            hardStop.invoke(deliverySystem);
            
            try {
                Thread.sleep(300);
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
        System.out.println("launch test");
        try {
            sender.sendMessage(new TestMessage(100));
            
            Thread.sleep(500);

            assertTrue("RequestLauncher should be launched", DeliverySystem.isLaunched());
            
            DeliverySystem.kill();
            
            Thread.sleep(500);
            
            assertTrue("RequestLauncher should be stoped", !DeliverySystem.isLaunched());
            
        } catch (SecurityException | InterruptedException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test 
    public void consomationTest() {
        System.out.println("consomation test");
        try {
        
            int messageDuration = 100;
            
            List<TestMessage> messages = new ArrayList<>();
            for(int i = 0; i < 10; i++) {
                messages.add(new TestMessage(messageDuration));
            }
            
            assertTrue("Il n'y a aucun future en début de test", futures.isEmpty());
            
            for(Message<?> m : messages) {
                m.setSenderId(sender.getSenderId());
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
    
    @Test
    public void stateTest() {

        System.out.println("Retrieve state test");
        
        int nbMessage = 50;
        int time = 100;
        
        List<TestMessage> messages = new ArrayList<>();
        for(int i = 0; i < nbMessage; i++) {
            messages.add(new TestMessage(time));
        }
        
        try {
             
            for(Message<?> m : messages) {
                m.setSenderId(sender.getSenderId());
                addTask.invoke(deliverySystem, m);
            }
            
            assertTrue("Number of message receive is " + messages.size(), futures.size() == messages.size());
           
            launchListener.invoke(deliverySystem);
            
            Thread.sleep(200);
            
            assertTrue("DeliverySystem is launched", (boolean)isActived.invoke(deliverySystem));
            
            safeStop.invoke(deliverySystem);
            
            Thread.sleep(500);
            
            assertTrue("DeliverySystem is stoped", !(boolean)isActived.invoke(deliverySystem));
            
            File f = new File(COOKIE_SWIPE_DIR);
            assertTrue("State file exist", f.exists());
            
            System.out.println("    Begin retrive ...");
            
            int nbTaskUndone = futures.size();
                        
            Constructor deliverySystemConstructor = DeliverySystem.class.getDeclaredConstructor();
            deliverySystemConstructor.setAccessible(true);

            deliverySystem = (DeliverySystem) deliverySystemConstructor.newInstance();
            //deliverySystemField.setAccessible(true);
            deliverySystemField.set(null, deliverySystem);
            
            Field field2 = DeliverySystem.class.getDeclaredField("futures");
            field2.setAccessible(true);
            futures = (ConcurrentLinkedQueue<Future<?>>) field2.get(deliverySystem);
            
            DeliverySystem.init();
            
            //Thread.sleep(700);
            
            assertTrue("Have retreive all task. Expected " + nbTaskUndone + " Received " 
                    + futures.size() , nbTaskUndone == futures.size());
            
            Thread.sleep(nbMessage*time);
            
            hardStop.invoke(deliverySystem);
            
            System.out.println("    Retreive done");
            
        } catch (IllegalAccessException
                |IllegalArgumentException
                |InvocationTargetException
                |SecurityException
                |InterruptedException
                |NoSuchFieldException
                |NoSuchMethodException
                |InstantiationException ex) {
            Logger.getLogger(DeliverySystemTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
