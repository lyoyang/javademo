package com.lyoyang.concurrent.threadpool;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCount {

    private static Object object = new Object();

    private static final AtomicInteger atomic = new AtomicInteger(0);

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        for(;;){
            new Thread(new Runnable(){
                public void run(){
                    synchronized(object){
                        count += 1;
                        System.out.println("Thread #"+count);
                    }
                    for(;;){
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e){
                            System.err.println(e);
                        }
                    }
                }
            }).start();
        }
    }


}
