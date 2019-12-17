package com.lyoyang.guava.eventbus;

import com.google.common.eventbus.EventBus;
import com.lyoyang.guava.eventbus.listener.SimpleListener;

public class SimpleEventBusExample {

    public static void main(String[] args) {
        final EventBus eventBus = new EventBus();
        eventBus.register(new SimpleListener());
        System.out.println("post the action");
        eventBus.post("buy a cup coffee");
    }

}
