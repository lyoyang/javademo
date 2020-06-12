package com.lyoyang.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: yangbing
 * @Date: 2020/5/11 10:26
 * @Description:
 * 不足：一次性的，计数器的值只能在构造器中初始化一次，使用完毕后不能再次使用
 * 等待所有的子线程执行完毕后，才进行主线程的业务逻辑
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch workLatch = new CountDownLatch(3);
        CountDownLatch startLatch = new CountDownLatch(1);
        for (int i = 0; i < 3; i++) {
            new Thread(new Worker(startLatch, workLatch)).start();
        }
        startLatch.countDown();
        workLatch.await();
        System.out.println("all worker finished");
    }

}

class Worker implements Runnable {
    private CountDownLatch startLatch;
    private CountDownLatch workerLatch;

    public Worker(CountDownLatch startLatch, CountDownLatch workerLatch) {
        this.startLatch = startLatch;
        this.workerLatch = workerLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
            System.out.println(Thread.currentThread().getName() + " are working...");
            TimeUnit.SECONDS.sleep(10);
            workerLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}