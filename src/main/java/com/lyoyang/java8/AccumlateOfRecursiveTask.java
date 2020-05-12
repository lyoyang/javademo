package com.lyoyang.java8;

import java.util.concurrent.RecursiveTask;

/**
 * 有返回值
 */
public class AccumlateOfRecursiveTask extends RecursiveTask<Integer> {


    private int start;
    private int end;
    private int[] data;

    private static final int LIMIT = 3;

    public AccumlateOfRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        if ((end - start) <= LIMIT) {
            for (int i = start; i < end; i++) {
                result += data[i];
            }
            return result;
        }
        int mid = (start + end) / 2;
        AccumlateOfRecursiveTask left = new AccumlateOfRecursiveTask(start, mid, data);
        AccumlateOfRecursiveTask right = new AccumlateOfRecursiveTask(mid, end, data);
        left.fork();
        Integer rightRes = right.compute();
        Integer leftRes = left.join();
        return leftRes + rightRes;
    }
}
