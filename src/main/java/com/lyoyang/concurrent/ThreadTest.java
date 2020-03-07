package com.lyoyang.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author: yangbing
 * @Date: 2020/2/19 16:33
 * @Description:
 */
public class ThreadTest {


    public static void main(String[] args) {
//        IntStream.range(0,2).mapToObj(ThreadTest::create).forEach(Thread::start);
        ThreadTest threadTest = new ThreadTest();
        for (int i = 0; i < 5; i++) {
            new Thread(threadTest::synchronizedTest).start();
        }
    }


    public static Thread create(int index) {
        return new Thread(() -> {
            if (index == 0) {
                Thread.yield();
            }
            System.out.println(index);
        });
    }


    private static final Object MONITOR = new Object();


    public void synchronizedTest() {
        synchronized (MONITOR) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized static void synchronizedTest2() {

    }

    public synchronized void synchronizedTest3() {

    }






}
