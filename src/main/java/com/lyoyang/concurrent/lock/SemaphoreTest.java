package com.lyoyang.concurrent.lock;

import java.util.concurrent.Semaphore;

public class SemaphoreTest implements Runnable {

    private int count = 0;

    private static final Semaphore semapjore = new Semaphore(1);

    @Override
    public void run() {
        try {
            addOne();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addOne() throws InterruptedException {
        semapjore.acquire();
        try {
            count += 1;
            System.out.println(count);
        } finally {
            semapjore.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        for (int i = 0; i < 1000; i++) {
            new Thread(semaphoreTest).start();
        }

    }





}
