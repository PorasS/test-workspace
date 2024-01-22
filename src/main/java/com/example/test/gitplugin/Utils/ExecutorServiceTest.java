package com.example.test.gitplugin.Utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

    public static Map<String, ExecutorService> esm = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        executeTask("123");
        executeTask("123");
    }

    public static void executeTask(String cid) {
        ExecutorService ex = null;
        if (esm.containsKey(cid)) {
            ex = esm.get(cid);
            ex.shutdown();
            if (ex.isShutdown()) {
                ex = Executors.newSingleThreadExecutor();
                esm.put(cid, ex);
            }
        } else {
            ex = Executors.newSingleThreadExecutor();
            esm.put(cid, ex);
        }

        ex.execute(() -> {
            try {
                System.out.println("Task Running for :" + cid);
                Thread.sleep(60000);
                System.out.println("Task Completed for :" + cid);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
