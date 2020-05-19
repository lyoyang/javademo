package com.lyoyang.concurrent.queue;

import com.sun.java.swing.plaf.windows.resources.windows;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: yangbing
 * @Date: 2020/2/18 10:51
 * @Description:
 */
public class QueueTest {

    public static void main(String[] args) {
    }

    @Test
    public void testQueueMethod() throws InterruptedException {
//        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);
//        blockingQueue.put("1");
//        blockingQueue.put("2");
//        blockingQueue.put("3");
//        blockingQueue.put("4");
//        blockingQueue.put("5");
//        blockingQueue.put("6");
//
//        System.out.println("BLOCKED");
//        System.out.println("peek==>" + blockingQueue.peek());
//        System.out.println(blockingQueue);
//        System.out.println("pool==>" + blockingQueue.poll());
//        System.out.println(blockingQueue);
        Windows windows = new Windows();
        new Thread(new Producer(windows)).start();
//        new Thread(new Producer(windows)).start();
//        new Thread(new Producer(windows)).start();
//        new Thread(new Consumer(windows)).start();
        new Thread(new Consumer(windows)).start();
        new Thread(new Consumer(windows)).start();
        new Thread(new Consumer(windows)).start();
        new Thread(new Consumer(windows)).start();
        Thread.currentThread().join();
    }


    static class Windows {
        private static final ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);
        private static final AtomicInteger atomicInteger = new AtomicInteger(1);

        public int produce() throws InterruptedException {
            int data = atomicInteger.getAndIncrement();
            blockingQueue.put(data);
            return data;
        }

        public int consume() throws InterruptedException {
            int data = blockingQueue.take();
            return data;
        }
    }

    class Producer implements Runnable {

        private final Windows windows;

        public Producer(Windows windows) {
            this.windows = windows;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(300);
                    int produce = windows.produce();
                    System.out.println("生产数据：" + produce);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    class Consumer implements Runnable {

        private final Windows windows;

        public Consumer(Windows windows) {
            this.windows = windows;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    int consume = windows.consume();
                    System.out.println("消费数据：" + consume);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
