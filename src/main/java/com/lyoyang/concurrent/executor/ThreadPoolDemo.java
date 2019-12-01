package com.lyoyang.concurrent.executor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.lyoyang.entity.User;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    private static ThreadPoolExecutor myEcecutor = new ThreadPoolExecutor(30, 30, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000), new ThreadPoolExecutor.CallerRunsPolicy());

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    private static int corePoolSize  = Runtime.getRuntime().availableProcessors() * 2;

    private static ExecutorService commonThreadPool = new ThreadPoolExecutor(corePoolSize, corePoolSize, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512), new ThreadPoolExecutor.DiscardPolicy());


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
