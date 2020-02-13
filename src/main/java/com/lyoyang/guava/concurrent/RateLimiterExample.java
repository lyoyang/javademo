package com.lyoyang.guava.concurrent;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.lang.Thread.*;

public class RateLimiterExample {
    /**1秒中允许有0.5次的操作**/
    private static final RateLimiter limiter = RateLimiter.create(0.5);
    /**同一时刻最多允许有3个线访问**/
    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10).forEach(i -> {
            executorService.submit(RateLimiterExample::testSemaphore);
        });
    }


    private static void testLimiter() {
        System.out.println(currentThread() + " waiting " + limiter.acquire());
    }

    private static void testSemaphore() {
        try {
            semaphore.acquire();
            System.out.println(currentThread() + " is coming and work...");
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println(currentThread() + " is release the semaphore.");
        }
    }





}
