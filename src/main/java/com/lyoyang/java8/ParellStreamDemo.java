package com.lyoyang.java8;

import com.google.common.base.Stopwatch;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParellStreamDemo {


    public static void main(String[] args) {
        System.out.println("the best process time of iterativeSum==>" + measureSumPerformance(ParellStreamDemo::iterativeSum, 10_000_000) + "MS");
        System.out.println("the best process time of normalAdd==>" + measureSumPerformance(ParellStreamDemo::normalAdd, 10_000_000) + "MS");
        System.out.println("the best process time of parallelStreamSum==>" + measureSumPerformance(ParellStreamDemo::parallelStreamSum, 10_000_000) + "MS");
        System.out.println("the best process time of parallelStreamSum2==>" + measureSumPerformance(ParellStreamDemo::parallelStreamSum2, 10_000_000) + "MS");
        System.out.println("the best process time of parallelStreamSum3==>" + measureSumPerformance(ParellStreamDemo::parallelStreamSum3, 10_000_000) + "MS");
    }




    public static long measureSumPerformance(Function<Long, Long> adder, long limit) {
        long fastertest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTime = System.currentTimeMillis();
            long result = adder.apply(limit);
            long duration = System.currentTimeMillis()-startTime;
            if (duration < fastertest) {
                fastertest = duration;
            }
        }
        return fastertest;
    }



    public static long iterativeSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n).reduce(0L, Long::sum);
    }

    public static long normalAdd(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }





    public static long parallelStreamSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .parallel().limit(n).reduce(0L, Long::sum);
    }

    public static long parallelStreamSum2(long n) {
        return Stream.iterate(1L, i -> i + 1).mapToLong(Long::longValue)
                .parallel().limit(n).reduce(0, Long::sum);
    }


    public static long parallelStreamSum3(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
    }


}
