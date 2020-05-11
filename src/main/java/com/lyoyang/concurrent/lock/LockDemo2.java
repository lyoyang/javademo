package com.lyoyang.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁测试
 */
public class LockDemo2 extends Thread {
    public ReentrantLock reentrantLock = new ReentrantLock();

    public void get() {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName());
        set();
        reentrantLock.unlock();
    }

   public void set() {
       reentrantLock.lock();
       System.out.println(Thread.currentThread().getName());
       reentrantLock.unlock();
   }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        LockDemo2 demo2 = new LockDemo2();
        new Thread(demo2).start();
        new Thread(demo2).start();
        new Thread(demo2).start();
        new Thread(demo2).start();
    }
}
