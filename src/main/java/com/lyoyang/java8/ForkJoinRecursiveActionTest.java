package com.lyoyang.java8;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ForkJoinRecursiveActionTest {

    private static final int MAX_THRESHOLD = 10;

    private static final AtomicInteger SUM = new AtomicInteger(0);

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new CalculationRecursiveAction(0, 1000));
        forkJoinPool.awaitQuiescence(10, TimeUnit.SECONDS);
        Optional.of(SUM).ifPresent(System.out::println);
    }


    private static class CalculationRecursiveAction extends RecursiveAction {

        private final int start;

        private final int end;

        public CalculationRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_THRESHOLD) {
                SUM.getAndAdd(IntStream.rangeClosed(start, end).sum());
            } else {
                int middle = (start + end) / 2;
                CalculationRecursiveAction left = new CalculationRecursiveAction(start, middle);
                CalculationRecursiveAction right = new CalculationRecursiveAction(middle + 1, end);
                left.fork();
                right.fork();
            }
        }
    }


}
