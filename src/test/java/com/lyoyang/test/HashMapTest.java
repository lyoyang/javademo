package com.lyoyang.test;

import sun.misc.VM;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapTest {


    public static void main(String[] args) {
        boolean booted = VM.isBooted();
        System.out.println(booted);
    }





    static class MyThread extends Thread {
        private static AtomicInteger ai = new AtomicInteger(0);
        //初始化一个table长度为1的哈希表
        private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(1);

        public void run() {
            while (ai.get() < 100000) {  //不断自增
                map.put(ai.get(), ai.get());
                ai.incrementAndGet();
            }
            System.out.println(Thread.currentThread().getName() + "线程即将结束");
        }


    }



}
