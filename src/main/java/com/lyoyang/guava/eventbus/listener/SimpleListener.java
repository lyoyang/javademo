package com.lyoyang.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;

public class SimpleListener {


    @Subscribe
    public void doAction(String action) {
        System.out.println("I get the job:" + action);
    }

}
