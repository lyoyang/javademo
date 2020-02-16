package com.lyoyang.java8;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinTest {

    private static int[] data = {1,2,3,4,5,6,7,8,9};

    public static void main(String[] args) {
        AccumlateOfRecursiveTask task = new AccumlateOfRecursiveTask(0, data.length, data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int result = forkJoinPool.invoke(task);
        System.out.println("AccumlateOfRecursiveTask Result==>" + result);
        AccumlateOfRecursiveAction action = new AccumlateOfRecursiveAction(0, data.length, data);
        forkJoinPool.invoke(action);
        System.out.println("AccumlateOfRecursiveAction Res==>" + AccumlateOfRecursiveAction.AccumlateHelper.getResult());
    }

}
