package com.lyoyang.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.lyoyang.guava.eventbus.listener.SimpleListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleEventBusExample {

    private static final ExecutorService exector = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        final EventBus eventBus = new EventBus();
        final AsyncEventBus asyncEventBus = new AsyncEventBus(exector);
//        eventBus.register(new SimpleListener());
        asyncEventBus.register(new SimpleListener());
        System.out.println("post the action start...");
        eventBus.post("buy a cup coffee");
        asyncEventBus.post("buy a cup coffee");
        System.out.println("post the action end...");
//        TimeUnit.MILLISECONDS.sleep(1000);
//        exector.shutdown();
    }

}
