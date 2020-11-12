package com.lyoyang.java8;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJoinTest {


    private static final int MAX_THRESHOLD = 30;

    private static int[] data = {1,2,3,4,5,6,7,8,9};

    public static void main(String[] args) {
//        AccumlateOfRecursiveTask task = new AccumlateOfRecursiveTask(0, data.length, data);
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        int result = forkJoinPool.invoke(task);
//        System.out.println("AccumlateOfRecursiveTask Result==>" + result);
//        AccumlateOfRecursiveAction action = new AccumlateOfRecursiveAction(0, data.length, data);
//        forkJoinPool.invoke(action);
//        System.out.println("AccumlateOfRecursiveAction Res==>" + AccumlateOfRecursiveAction.AccumlateHelper.getResult());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new CalculateRecursiveTask(0, 1000));
        System.out.println(future);
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private static class CalculateRecursiveTask extends RecursiveTask<Integer> {

        private int start;

        private int end;

        public CalculateRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end -start < MAX_THRESHOLD) {
                return IntStream.rangeClosed(start, end).sum();
            } else {
                int middle = (start + end) / 2;
                CalculateRecursiveTask t1 = new CalculateRecursiveTask(start, middle);
                CalculateRecursiveTask t2 = new CalculateRecursiveTask(middle + 1, end);
                t1.fork();
                t2.fork();
                return t1.join() + t2.join();

            }
        }
    }

}
