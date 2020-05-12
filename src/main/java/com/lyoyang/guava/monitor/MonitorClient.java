package com.lyoyang.guava.monitor;

import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MonitorClient {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new FileChangeListener());
        DirectoryMonitor directoryMonitor = new DirectoryMonitor(eventBus, "/home/yangbing/tmp");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            try {
                directoryMonitor.stopMonitor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.SECONDS);
        executor.shutdown();
        try {
            directoryMonitor.startMonitor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
