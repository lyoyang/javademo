package com.lyoyang.test;

import java.util.concurrent.TimeUnit;

public class ThreadTest {


    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("123");
        });
//        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread process.");
    }


}
