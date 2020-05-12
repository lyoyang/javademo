package com.lyoyang.concurrent;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class WaitSet {

    private static final Object LOCK = new Object();

    /**
     *  1、所有的对象都会有一个wait set,用来存放调用了该对象wait方法之后的进入block状态的线程
     *  2、线程被notify之后不一定立即得到执行
     *  3、线程从wait set中被唤醒的顺序不一定是FIFO
     *  4、线程被唤醒后必须重新获取锁
     * @param args
     */
    public static void main(String[] args) {

        IntStream.rangeClosed(1,10).forEach(i -> {
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    synchronized (LOCK) {
                        try {
                            Optional.of(Thread.currentThread().getName() + " will into wait set.")
                                    .ifPresent(System.out::println);
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName() + " will leave wait set.")
                                    .ifPresent(System.out::println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.rangeClosed(1, 10).forEach(j -> {
            synchronized (LOCK) {
                LOCK.notify();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
