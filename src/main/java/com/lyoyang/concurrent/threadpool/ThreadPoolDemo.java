package com.lyoyang.concurrent.threadpool;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * 创建线程池的时候，究竟给多少给线程合适？
 * 首先的看下线程的工作类型，是CPU密集还是IO密集，或者是2者都有
 * 如果是CPU密集的话，一般是OS的内核数，但具体是多少，的根据具体测试来看
 * 不仅要考虑执行任务的时间，还得考虑线程创建以及线程上下文切换的时间
 *
 */

public class ThreadPoolDemo {

    private static final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);

    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    private static final ExecutorService stealingPool = Executors.newWorkStealingPool();


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
//        fixedThreadPoolTest();
//        Thread.currentThread().join();


        List<Callable<Long>> callables = IntStream.range(0, 100).boxed().map(i -> {
            return new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    long count = 0;
                    while (true) {
                        count++;
                        if (count > 10000000L) {
                            break;
                        }
                    }
                    return count;
                }
            };
        }).collect(Collectors.toList());

        List<Future<Long>> futures = fixedThreadPool.invokeAll(callables);
        System.out.println("cost time:" + (System.currentTimeMillis() - start));
//        ThreadPoolExecutor t = (ThreadPoolExecutor) fixedThreadPool;
//        System.out.println(t.getActiveCount());
//        TimeUnit.SECONDS.sleep(5);
//        System.out.println(t.getActiveCount());
//        fixedThreadPool.shutdownNow();
//        System.out.println(t.getActiveCount());
//        TimeUnit.SECONDS.sleep(5);
//        System.out.println(t.getActiveCount());



//        cachedThreadPool();
//        singleThreadPool();
//        stealThreadPoolWork();
//        Thread.currentThread().join();
    }



    public static void fixedThreadPoolTest() {
        IntStream.range(0, 100).boxed().forEach(i -> {
            fixedThreadPool.execute(() -> {
                long count = 0;
                while (true) {
                    count++;
                    if (count > 10000000L) {
                        break;
                    }
                }
//                System.out.println("this is fixed thread pool===>" + i);
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            });
        });
//        fixedThreadPool.execute(() -> {
//            boolean daemon = Thread.currentThread().isDaemon();
//            System.out.println("daemon:" + daemon);
//            System.out.println("this is fixed thread pool");
//        });
    }

    public static void cachedThreadPoolTest() {
        cachedThreadPool.execute(() -> {
            boolean daemon = Thread.currentThread().isDaemon();
            System.out.println("daemon:" + daemon);
            System.out.println("this is cached thread pool");
        });
    }

    public static void singleThreadPoolTest() {
        singleThreadExecutor.execute(() -> {
            boolean daemon = Thread.currentThread().isDaemon();
            System.out.println("daemon:" + daemon);
            System.out.println("this is single thread pool");
        });
    }

    public static void stealThreadPoolWorkTest() {
        stealingPool.execute(() -> {
            boolean daemon = Thread.currentThread().isDaemon();
            System.out.println("daemon:" + daemon);
            System.out.println("this is steal thread pool");
        });
    }




}
