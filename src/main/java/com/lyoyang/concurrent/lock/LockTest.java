package com.lyoyang.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    private final Lock rtl = new ReentrantLock();

    private int value;

    public void addOne() {
        rtl.lock();
        try {
            value += 1;
        } finally {
//            rtl.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockTest lockTest = new LockTest();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                lockTest.addOne();
            }).start();
        }

        Thread.sleep(1000L);
        System.out.println(lockTest.getValue());
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @Test
    public void testJoin() {
        int val = 0;

    }

}
