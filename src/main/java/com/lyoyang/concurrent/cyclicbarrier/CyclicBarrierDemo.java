package com.lyoyang.concurrent.cyclicbarrier;

import java.util.concurrent.*;

/**
 * @author: Brian
 * @Date: 2020/5/11 10:52
 * @Description:
 */
public class CyclicBarrierDemo {

    private static final int COUNT = 40;

    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(8);

//    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(8, new Runnable() {
////        @Override
////        public void run() {
////            System.out.println("all woker finished");
////        }
////    });

    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(5);

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            EXECUTOR.execute(new Worker(CYCLIC_BARRIER));
        }
    }


    static class Worker implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public Worker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is ready...");
            try {
//                TimeUnit.SECONDS.sleep(10);
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " are working now...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


}
