package com.lyoyang.guava.concurrent;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static java.lang.Thread.*;

public class BucketTest {

    public static void main(String[] args) {
        final BucketExample bucket = new BucketExample();
        final AtomicInteger atomicInteger = new AtomicInteger(0);

        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                for (;;) {
                    int data = atomicInteger.getAndIncrement();
                    bucket.submit(data);
                    try {
                        TimeUnit.MILLISECONDS.sleep(200L);
                    } catch (Exception e) {
                        if (e instanceof IllegalStateException) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }).start();
        });

        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                for (;;) {
                    bucket.takeAndConsume(x -> {
                        System.out.println(currentThread() + "W " + x);
                    });
                }
            }).start();
        });
    }

}
