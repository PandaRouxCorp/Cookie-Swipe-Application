/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network.messageFramework;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mickx
 */
public class DeliverySystem {

    private static DeliverySystem INSTANCE;
    private final Logger LOGGER;
    private ExecutorCompletionService<Object> completionService;
    private final ConcurrentLinkedQueue<Future<?>> futures;
    private ExecutorService slaveExecutor;
    private ExecutorService masterExecutor;
    private final Map<Future<?>, FrameworkMessage<?>> matcher;
    private volatile boolean isLaunched;
    private volatile boolean shouldStop;
    private volatile boolean safeStop;

    static {
        INSTANCE = new DeliverySystem();
    }

    private DeliverySystem() {
        LOGGER = Logger.getLogger(DeliverySystem.class.getName());
        slaveExecutor = Executors.newFixedThreadPool(4);
        completionService = new ExecutorCompletionService<>(slaveExecutor);
        futures = new ConcurrentLinkedQueue<>();
        matcher = new HashMap<>();
    }

    private void link(FrameworkMessage<?> callable, Future<?> f) {
        matcher.put(f, callable);
    }
    
    private void addTask(FrameworkMessage<?> callable) {
    	addTask(callable,true);
    }

    private void addTask(FrameworkMessage<?> callable, boolean shouldAnswer) {     
    	if(slaveExecutor.isShutdown()) {
            slaveExecutor = Executors.newFixedThreadPool(4);
            completionService = new ExecutorCompletionService<>(slaveExecutor);
        }
        if(callable != null) {
            @SuppressWarnings("unchecked")
			Future<Object> f = completionService.submit((FrameworkMessage<Object>) callable);
            if(shouldAnswer) link(callable, f);
            futures.add(f);
        }
    }
    
    public static void launchTask(FrameworkMessage<?> callable) {
    	if (INSTANCE.isActived() == false) {
            INSTANCE.launchListener();
        }
        INSTANCE.addTask(callable,false);
    }

    private void onRecieveResponse(Future<?> f) {
        futures.remove(f);
        if(matcher.get(f) != null) {
        	Postman.sendResponse(matcher.get(f).getSenderId(), f, matcher.get(f));
        }
        matcher.remove(f);
    }

    private void launchListener() {
//        masterExecutor = Executors.newSingleThreadExecutor();
//        masterExecutor.execute(() -> {
//            safeStop = false;
//            shouldStop = false;
//            isLaunched = true;
//            while (!shouldStop) {
//                try {
//                    if (!futures.isEmpty()) {
//                        onRecieveResponse(completionService.take());
//                    } else {
//                        try {
//                            Thread.sleep(100);
//                        } catch (InterruptedException e) {
//                            break;
//                        }
//                    }
//                } catch (Exception e) {
//                    LOGGER.log(Level.SEVERE, "Erreur :", e);
//                }
//            }
//            if (safeStop) {
//                saveState();
//            }
//            isLaunched = false;
//            slaveExecutor.shutdownNow();
//        });
        
        masterExecutor.execute(new Runnable() {

            @Override
            public void run() {
                safeStop = false;
                shouldStop = false;
                isLaunched = true;
                while (!shouldStop) {
                    try {
                        if (!futures.isEmpty()) {
                            onRecieveResponse(completionService.take());
                        } else {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                break;
                            }
                        }
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "Erreur :", e);
                    }
                }
                if (safeStop) {
                    saveState();
                }
                isLaunched = false;
                slaveExecutor.shutdownNow();
            }
        });
    }

    public int getTaskNumber() {
        return futures.size();
    }

    private boolean isActived() {
        return isLaunched;
    }

    private void hardStop() {
        shouldStop = true;
    }

    private void safeStop() {
        safeStop = true;
        shouldStop = true;
    }

    private void saveState() {
        List<FrameworkMessage<?>> frameworkMessages = new ArrayList<>();
        
//        futures.stream().forEach((f) -> {
//            frameworkMessages.add(matcher.get(f));
//        });
        
        for(Future<?> f : futures) {
            frameworkMessages.add(matcher.get(f));
        }
        
        Postman.serializeMessages(frameworkMessages);
    }

    private void retreiveState() {
        Postman.retreiveSavedMessages();
    }

    public static boolean isLaunched() {
        return INSTANCE.isActived();
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

    static int getCurrentTaskNumber() {
        return INSTANCE.getTaskNumber();
    }

    static void launch(FrameworkMessage<?> message) { // Droit package ... Passer par Postman
        if (INSTANCE.isActived() == false) {
            INSTANCE.launchListener();
        }
        INSTANCE.addTask((FrameworkMessage<?>) message);
    }
    
    public static void init() {
        INSTANCE.retreiveState();
    }
}
