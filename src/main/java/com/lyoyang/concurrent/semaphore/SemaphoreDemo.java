package com.lyoyang.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: Brian
 * @Date: 2020/5/11 10:38
 * @Description: 信号量，用来做限流
 */
public class SemaphoreDemo {

    private static final int COUNT = 40;

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(40);

    /**
     * 最多只有10个线程在执行操作
     */
    private static final Semaphore semaphore = new Semaphore(10);


    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            EXECUTOR.execute(new Task(semaphore));
        }
    }

    static class Task implements Runnable {

        private Semaphore semaphore;

        public Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " are working...");
                TimeUnit.SECONDS.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
