/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mickx
 */
public class RequestLauncher {

    
    
    private static RequestLauncher INSTANCE;
    private final Logger LOGGER;
    private volatile CompletionService<Object> completionService;
    private final ConcurrentLinkedQueue<Future<Object>> futures;
    private volatile Executor slaveExecutor;
    private volatile Executor masterExecutor;
    private final Map<Future,Message<Object>> matcher;

    private RequestLauncher() {
        LOGGER = Logger.getLogger(RequestLauncher.class.getName());
        slaveExecutor = Executors.newFixedThreadPool(4);
        completionService = new ExecutorCompletionService<>(slaveExecutor);
        futures = new ConcurrentLinkedQueue<>();
        matcher = new HashMap<>();
    }
    
    private void link(Message<Object> callable, Future f) {
        matcher.put(f, callable);
    }

    private void addTask(Message<Object> callable) {
        Future f = completionService.submit(callable);
        link(callable, f);
        futures.add(f);
    }
    
    private void onRecieveResponse(Future<Object> f) {
        futures.remove(f);
        Postman.sendResponse(matcher.get(f).getSender(),f);
        matcher.remove(f);
    }
    
    private void launchListener() {
        masterExecutor = Executors.newSingleThreadExecutor();
        masterExecutor.execute(() -> {
            while (true) {
                try {
                    if (!futures.isEmpty()) {
                        onRecieveResponse(completionService.take());
                    }
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Erreur :", e);
                }
            }
        });
    }
    
    private void hardStop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void safeStop() {
        saveState();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void stop() {
        if (INSTANCE != null) {
            INSTANCE.safeStop();
        }
    }
    
    public static void kill() {
        if (INSTANCE != null) {
            INSTANCE.hardStop();
        }
    }

    static void launch(Message<Object> message) { // Droit package ... Passer par Postman
        if (INSTANCE == null) {
            INSTANCE = new RequestLauncher();
            INSTANCE.launchListener();
        }
        INSTANCE.addTask(message);
    }
}
