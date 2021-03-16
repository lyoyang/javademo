package com.lyoyang.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RTTLockTest {

    private double x, y;

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock readLock = lock.readLock();
    private Lock writeLock = lock.writeLock();

    public double read() {
        readLock.lock();
        try {
            return Math.sqrt(x * x + y * y);
        } finally {
            readLock.unlock();
        }
    }

    public void move(double dx, double dy) {
        writeLock.lock();
        try {
            x += dx;
            y += dy;
        } finally {
            writeLock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        RTTLockTest rttLockTest = new RTTLockTest();

        new Thread(() -> {
            double read = rttLockTest.read();
            System.out.println("read:" + read);
        }).start();
        new Thread(() -> {
            double read = rttLockTest.read();
            System.out.println("read:" + read);
        }).start();
        new Thread(() -> {
            double read = rttLockTest.read();
            System.out.println("read:" + read);
        }).start();
        new Thread(() -> {
            rttLockTest.move(2, 5);
            System.out.println("write...");
        }).start();

        TimeUnit.SECONDS.sleep(1000);


    }



}
