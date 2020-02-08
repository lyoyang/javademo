package com.lyoyang.concurrent;

import java.util.function.Function;

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
        long calc = calc();
        System.out.println(calc);
        Function<String, Integer> lamda = s -> s.length();
    }






}
