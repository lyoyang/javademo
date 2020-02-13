package com.lyoyang.guava.concurrent;

import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

import static java.lang.Thread.currentThread;

/**
 * 漏桶算法
 */
public class BucketExample {

    private final ConcurrentLinkedQueue<Integer> container = new ConcurrentLinkedQueue<>();
    private static final int BUCKET_LIMIT = 1000;
    private static final RateLimiter limiter = RateLimiter.create(10);
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
}
