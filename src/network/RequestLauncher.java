/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
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
    private Logger LOGGER;
    private CompletionService<Object> completionService;
    private ConcurrentLinkedQueue<Future<Object>> futures;
    private Executor executor;

    private RequestLauncher() {
        LOGGER = Logger.getLogger(RequestLauncher.class.getName());
        completionService = new ExecutorCompletionService<>(
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
        );
        futures = new ConcurrentLinkedQueue<>();
        executor = Executors.newSingleThreadExecutor();
    }

    private void addTask(Callable<Object> callable) {
        futures.add(completionService.submit(callable));
    }
    
    private void launchListener() {
        executor.execute(new Runnable() {

            public void run() {
                while (true) {
                    try {
                        if (!futures.isEmpty()) {
                            try {
                                Future future = completionService.take();
                                futures.remove(future);
                                Object o = future.get();
                                if (o != null) {
                                    
                                    // Opérations ...
                                    System.out.println(o);
                                }
                            } catch (ExecutionException e1) {
                                LOGGER.log(Level.SEVERE,"Erreur :",e1);
                            }
                        }
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE,"Erreur :",e);
                    }
                }
            }
        });
    }

    public static void launch(Callable<Object> callable) {
        if (INSTANCE == null) {
            INSTANCE = new RequestLauncher();
            INSTANCE.launchListener();
        }
        INSTANCE.addTask(callable);
    }
}
