package com.lyoyang.concurrent.executor;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class AddWorker implements Runnable {

    private int data = 0;

    @SneakyThrows
    @Override
    public void run() {
        for (int j = 0; j < 500; j++) {
            System.out.println(Thread.currentThread() + " data is:" + (++data));
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddWorker addWorker = new AddWorker();
        for (int j = 0; j < 10; j++) {
            new Thread(addWorker).start();
        }
//        Thread.currentThread().join();
//        System.out.println(addWorker.data);

    }

}
