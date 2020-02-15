package com.lyoyang.java8;

import com.google.common.base.Stopwatch;

import java.util.stream.Stream;

public class ParellStreamDemo {


    public static void main(String[] args) {
        sequentialSum(10);
        iterativeSum(10);
        parallelSum(10);
    }


    public static void sequentialSum(long n) {
        Stopwatch started = Stopwatch.createStarted();
        Long sum = Stream.iterate(1L, i -> i + 1)
                .limit(n).reduce(0L, Long::sum);
        System.out.println("sequentialSum:" + sum + ",cost time:" + started.stop());
    }


    public static void iterativeSum(long n) {
        long result = 0;
        Stopwatch started = Stopwatch.createStarted();
        for (long i = 0; i <=n; i++) {
            result += n;
        }
        System.out.println("iterativeSum:" + result + ",cost time:" + started.stop());
    }


    public static void parallelSum(long n) {
        Stopwatch started = Stopwatch.createStarted();
        Long reduce = Stream.iterate(0L, i -> i + 1)
                .limit(n).parallel().reduce(0L, Long::sum);
        System.out.println("parallelSum:" + reduce + ", cost time:" + started.stop());
    }


}
