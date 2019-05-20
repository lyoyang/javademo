package com.lyoyang.concurrent.executor;

import com.google.common.collect.Lists;
import com.lyoyang.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo {
    private static ThreadPoolExecutor myEcecutor = new ThreadPoolExecutor(30, 30, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000), new ThreadPoolExecutor.DiscardPolicy());

    private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);


    private class NumberRunner implements Callable<String> {

        private Integer num;
        public NumberRunner(Integer num) {
            this.num = num;
        }

        @Override
        public String call() throws Exception {

            double res = 0;
            try {
                res = 9/num;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("the result is:" + res);
            return "ok";
        }
    }


    private class MyRunner implements Callable<String> {

        private User user;

        public MyRunner(User user) {
            this.user = user;
        }

        @Override
        public String call() throws Exception {
            System.out.println(user.toString());
            return "ok";
        }
    }


    private class MyRunnerForInteger implements Callable<String> {

        private Integer num;

        public MyRunnerForInteger(Integer num) {
            this.num = num;
        }

        @Override
        public String call() throws Exception {
            User user = new User();
            user.setId(num);
            user.setAge(num + 10);
            System.out.println(user.toString());
            return "ok";
        }
    }

    /**
     *
     */
    @Test
    public void test_exector() {
        System.out.println("#########exector start##########");
        long begin = System.currentTimeMillis();
        ArrayList<User> list = Lists.newArrayList();
//        User u1 = new User(1,12,"jim", "北京");
//        User u2 = new User(2,13,"bob", "天津");
//        User u3 = new User(3,12,"braace", "澳门");
//        list.add(u1);
//        list.add(u2);
//        list.add(u3);
        for(int i=0; i<list.size(); i++) {
            myEcecutor.submit(new MyRunner(list.get(i)));
        }
        System.out.println("time:" + (System.currentTimeMillis() - begin));
        System.out.println("##########exector end############");
    }


    /**
     * 会发生多个线程共用同一个user
     */
    @Test
    public void test_exector2() {
        System.out.println("#########exector start##########");
        long begin = System.currentTimeMillis();
        User user = new User();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        for(int i=0; i<list.size(); i++) {
            user.setAge(i + 10);
            user.setId(i);
            myEcecutor.submit(new MyRunner(user));
        }
        System.out.println("time:" + (System.currentTimeMillis() - begin));
        System.out.println("##########exector end############");
    }


    /**
     * 把具体的逻辑都放线程里边去执行
     */
    @Test
    public void test_exector3() {
        System.out.println("#########exector start##########");
        long begin = System.currentTimeMillis();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        for(int i=0; i<list.size(); i++) {
            myEcecutor.submit(new MyRunnerForInteger(list.get(i)));
        }
        System.out.println("time:" + (System.currentTimeMillis() - begin));
        System.out.println("##########exector end############");
    }


    @Test
    public void test_noMoreThread() {
        System.out.println("#########exector start##########");
        long begin = System.currentTimeMillis();
        ArrayList<User> list = Lists.newArrayList();
//        User u1 = new User(1,12,"jim", "北京");
//        User u2 = new User(2,13,"bob", "天津");
//        User u3 = new User(3,12,"braace", "澳门");
//        list.add(u1);
//        list.add(u2);
//        list.add(u3);
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
        System.out.println("time:" + (System.currentTimeMillis() - begin));
        System.out.println("##########exector end############");
    }


    @Test
    public void test_exception() {
        List<Integer> list = Arrays.asList(1, 23, 3, 0, 4, 7, 8);
        List<Callable<String>> callables = new ArrayList<Callable<String>>();
        for(Integer i : list) {
            callables.add(new NumberRunner(i));
        }
        try {
            fixedThreadPool.invokeAll(callables);
        } catch (InterruptedException e) {
            System.out.println("主线程出错了。。。。。。");
        }
    }



}
