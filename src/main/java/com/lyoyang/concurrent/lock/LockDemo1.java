package com.lyoyang.concurrent.lock;

/**
 * 可重入锁（递归锁）
 * 指在同一线程 外层函数获得锁之后 ，内层递归函数仍然有获取该锁的代码，但不受影响
 */
public class LockDemo1 implements Runnable {

    public synchronized void get() {
        System.out.println("name:" + Thread.currentThread().getName() + " get()");
        set();
    }

    public synchronized void set() {
        System.out.println("name:" + Thread.currentThread() + " set()");
    }


    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        LockDemo1 demo1 = new LockDemo1();
        new Thread(demo1).start();
        new Thread(demo1).start();
        new Thread(demo1).start();
        new Thread(demo1).start();
        new Thread(demo1).start();
    }
}
