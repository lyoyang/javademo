package com.lyoyang.concurrent.executor;

import java.util.concurrent.*;

public class ExecutorDemo {

    private static final Executor threadPoolExecutor = new ThreadPoolExecutor(2, 3,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(2));


    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <=6; i++) {
            threadPoolExecutor.execute(new InnerWorker(i));
        }
        Thread.currentThread().join();
    }



    static class InnerWorker implements Runnable {
        private int data;

        public InnerWorker(int data) {
            this.data = data;
        }

        @Override
        public void run() {
            if (data < 5) {
                try {
                    TimeUnit.MINUTES.sleep(1);
                    System.out.println(Thread.currentThread() + "work,data=" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(Thread.currentThread() + "work,data=" + data);
            }
        }
    }

}
