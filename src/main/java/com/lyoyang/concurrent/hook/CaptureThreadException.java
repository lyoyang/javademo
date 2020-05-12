package com.lyoyang.concurrent.hook;

import java.util.concurrent.TimeUnit;

public class CaptureThreadException {


    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " occured exception.");
            e.printStackTrace();
        });

        final Thread thread = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a = 1 / 0;
        }, "Test-Thread");
        thread.start();
    }


}
