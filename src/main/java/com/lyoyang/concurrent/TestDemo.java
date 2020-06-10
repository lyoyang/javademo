package com.lyoyang.concurrent;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.IntStream;

public class TestDemo {

    private static long count = 0;

    private synchronized void add() {
        int index = 0;
        while (index++ < 1000000) {
            count += 1;
        }
    }


    public static long calc() throws InterruptedException {
        final TestDemo testDemo = new TestDemo();
        Thread t1 = new Thread(() -> testDemo.add());

        Thread t2 = new Thread(() -> testDemo.add());
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        return count;

    }


    public static void main(String[] args) throws InterruptedException {
//        Random random = new Random();
//        int min = 60;
//        int max = 100;
//        for (int i = 0; i < 200; i++) {
//            int s = random.nextInt(max)%(max-min+1) + min;
//            System.out.println(s);
//        }
        System.out.println(calc());
    }








}
