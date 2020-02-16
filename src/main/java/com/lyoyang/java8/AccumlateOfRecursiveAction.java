package com.lyoyang.java8;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class AccumlateOfRecursiveAction extends RecursiveAction {

    private int start;
    private int end;
    private int[] data;
    private static final int LIMIT = 3;

    public AccumlateOfRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        if ((end - start) <= LIMIT) {
            for (int i = start; i < end; i++) {
                AccumlateHelper.accumlate(data[i]);
            }
        } else {
            int mid = (start + end) / 2;
            AccumlateOfRecursiveAction left= new AccumlateOfRecursiveAction(start, mid, data);
            AccumlateOfRecursiveAction right = new AccumlateOfRecursiveAction(mid, end, data);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }

    static class AccumlateHelper {
        private static final AtomicInteger atomicInteger = new AtomicInteger(0);

        static void accumlate(int data) {
            atomicInteger.getAndAdd(data);
        }
        static int getResult() {
            return atomicInteger.get();
        }

        static void reset() {
            atomicInteger.set(0);
        }
    }


}
