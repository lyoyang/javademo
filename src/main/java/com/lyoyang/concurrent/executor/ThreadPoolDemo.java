package com.lyoyang.concurrent.executor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lyoyang.entity.User;
import lombok.ToString;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDemo {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolDemo.class);

    private static ThreadPoolExecutor myEcecutor = new ThreadPoolExecutor(30, 30, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000), new ThreadPoolExecutor.CallerRunsPolicy());

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    private static int corePoolSize  = Runtime.getRuntime().availableProcessors() * 2;

    private static ExecutorService commonThreadPool = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512), new ThreadPoolExecutor.DiscardPolicy());

    private static final ExecutorService CHECK_THREAD_POOL = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));


    private static final ExecutorService CHECK_THREAD_POOL2 = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), r->{
        Thread thread = new Thread(r);
        thread.setDaemon(false);
        return thread;
    }, new ThreadPoolExecutor.AbortPolicy());

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);


    public static void main(String[] args) {
        testThreadPool();
    }



    public static void testThreadPool()  {
        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            callables.add(() -> {
                try {
                    LOG.info("this is no " + atomicInteger.getAndIncrement());
                } catch (Exception e) {
                    LOG.error("线程池数量不足", e);
                }
                return null;
            });
        }
//            try {
//                for (int i = 0; i < 100; i++) {
//                    CHECK_THREAD_POOL.execute(() -> {
//                        try {
//                            LOG.info("this is no " + atomicInteger.getAndIncrement());
//                            Thread.sleep(1000);
//                        } catch (Exception e) {
//                            LOG.error("线程池数量不足", e);
//                        }
//                    });
//                }
//            } catch (Exception e) {
//                LOG.error("主线程异常", e);
//            }

        try {
            CHECK_THREAD_POOL2.invokeAll(callables);
        } catch (Exception e) {
            LOG.error("主线程发生异常", e);
        }
        CHECK_THREAD_POOL2.shutdown();
    }

    final void testFinal() {

    }

    @Test
    public void doInvokeTask() {
        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            callables.add(new TaskRunner("user-->" + i));
        }
        try {
            List<Future<String>> futures = myEcecutor.invokeAll(callables);
            System.out.println("处理任务数：" + futures.size() + "###处理任务--》" + JSONObject.toJSONString(futures));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void doSubmitTask() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Future<String> submit = myEcecutor.submit(new TaskRunner("user-->" + i));
            String res = null;
//            try {
//                res = submit.get();
//                if (submit.isDone()) {
//                    System.out.println(res + "执行成功：" + i);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        }
    }

    static class TaskRunner implements Callable<String> {

        private String name;

        public TaskRunner(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.MILLISECONDS.sleep(10);
            System.out.println(Thread.currentThread() + "----->" + name);
            return "OK";
        }
    }
}