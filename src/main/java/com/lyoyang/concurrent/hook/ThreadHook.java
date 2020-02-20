package com.lyoyang.concurrent.hook;

import java.util.concurrent.TimeUnit;

public class ThreadHook {

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("the hook thread1 is running ...");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the hook thred1 will exit.");
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("the hook thread2 is running ...");
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("the hook thred2 will exit.");
            }
        });

        System.out.println("the programe is stopping.");
    }


}
