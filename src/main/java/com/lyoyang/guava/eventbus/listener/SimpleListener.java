package com.lyoyang.guava.eventbus.listener;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleListener.class);


    @Subscribe
    public void doAction(String action) {
        System.out.println("I get the job:" + action);
    }

}
