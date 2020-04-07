package com.lyoyang.concurrent;

public class VolatileTest2 {

    private volatile static int INIT_VALUE = 0;



    private static final int MAX_VALUE = 50;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_VALUE) {
                System.out.printf("the value updated to [%d] \n", ++INIT_VALUE);
            }
        }, "READER").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_VALUE) {
                System.out.printf("update the value to [%d] \n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();
    }
}
