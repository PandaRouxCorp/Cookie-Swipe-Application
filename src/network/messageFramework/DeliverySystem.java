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
public class DeliverySystem {

    private static final DeliverySystem INSTANCE;
    private final Logger LOGGER;
    private final CompletionService<Object> completionService;
    private final ConcurrentLinkedQueue<Future<?>> futures;
    private final Executor slaveExecutor;
    private Executor masterExecutor;
    private final Map<Future, Message<?>> matcher;
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
        retreiveState();
    }

    private void link(Message<?> callable, Future f) {
        matcher.put(f, callable);
    }

    private void addTask(Message<?> callable) {
        Future f = completionService.submit((Message<Object>) callable);
        link(callable, f);
        futures.add(f);
    }

    private void onRecieveResponse(Future<?> f) {
        futures.remove(f);
        Postman.sendResponse(matcher.get(f).getSender(), f);
        matcher.remove(f);
    }

    private void launchListener() {
        masterExecutor = Executors.newSingleThreadExecutor();
        masterExecutor.execute(() -> {
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
        List<Message> messages = new ArrayList<>();
        futures.stream().map((f) -> matcher.get(f)).forEach((m) -> {
            messages.add(m);
        });
        Postman.serializeMessages(messages);
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

    static void launch(Message<?> message) { // Droit package ... Passer par Postman
        if (INSTANCE.isActived() == false) {
            INSTANCE.launchListener();
        }
        INSTANCE.addTask((Message<Object>) message);
    }
}
