package com.lyoyang.guava.concurrent;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import static java.lang.Thread.currentThread;

/**
 * 漏桶算法
 */
public class BucketExample {

    private final ConcurrentLinkedQueue<Integer> container = new ConcurrentLinkedQueue<>();
    private static final int BUCKET_LIMIT = 100;
    private static final RateLimiter limiter = RateLimiter.create(0.1);
    private static final Monitor offMonitor = new Monitor();
    private static final Monitor pollMonitor = new Monitor();

    public void submit(int data) {
        if (offMonitor.enterIf(offMonitor.newGuard(() -> container.size() < BUCKET_LIMIT))) {

            try {
                container.offer(data);
                System.out.println(currentThread() + " submit data:" + data + ",currentSize=" + container.size());
            } finally {
                offMonitor.leave();
            }
        } else {
            throw new IllegalStateException("the bucket is full.");
        }
    }

    public void takeAndConsume(Consumer<Integer> consumer) {
        if (pollMonitor.enterIf(pollMonitor.newGuard(() -> !container.isEmpty()))) {
            try {
                System.out.println(currentThread() + " waiting " + limiter.acquire());
                consumer.accept(container.poll());
            } finally {
                pollMonitor.leave();
            }
        }
    }


    @Test
    public void testLimiter() {
        //1s生成0.1个令牌，即10s生成1个令牌
        RateLimiter mylimiter = RateLimiter.create(0.1);
        for (int i = 0; i < 20; i++) {
            double acquire = mylimiter.acquire();
            System.out.println("acquire=" + acquire + "," + i);
        }
    }

}
