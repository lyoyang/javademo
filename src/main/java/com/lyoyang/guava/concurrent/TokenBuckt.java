package com.lyoyang.guava.concurrent;

import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;

public class TokenBuckt {

    private AtomicInteger phoneNumber = new AtomicInteger(0);
    private static final int LIMIT = 100;
    private RateLimiter limiter = RateLimiter.create(10);
    private final int saleLimit;

    public TokenBuckt() {
        this(LIMIT);
    }

    public TokenBuckt(int saleLimit) {
        this.saleLimit = saleLimit;
    }

    public int buy() {
        Stopwatch started = Stopwatch.createStarted();
        boolean success = limiter.tryAcquire(10, TimeUnit.SECONDS);
        if (success) {
            if (phoneNumber.get() >= LIMIT) {
                throw new IllegalStateException("Sorry, not any phone be saled");
            }
            int phone = phoneNumber.getAndIncrement();
            handleOrder();
            System.out.println(currentThread() + " user get the phone:" + phone + ",ELT:" + started.stop());
            return phone;
        } else {
            started.stop();
            throw new RuntimeException("Sorry, not get phone, please try again.");
        }
    }

    private void handleOrder() {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

