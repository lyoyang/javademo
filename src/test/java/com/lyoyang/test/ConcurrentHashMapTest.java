package com.lyoyang.test;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ConcurrentHashMapDemo());
        Thread t2 = new Thread(new ConcurrentHashMapDemo());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(ConcurrentHashMapDemo.concurrentHashMap);
    }





    @Test
    public void test2() {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        Integer v1 = concurrentHashMap.put("jim", 0);
        System.out.println(v1);
        Integer v2 = concurrentHashMap.put("jim", 23);
        System.out.println(v2);
        Integer v3 = concurrentHashMap.putIfAbsent("jim", 30);
        System.out.println(v3);
        System.out.println(concurrentHashMap);

    }




    static class ConcurrentHashMapDemo implements Runnable {
        public static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();

        static {
            concurrentHashMap.put("小明", 0);
        }


        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                while (true) {
                    Integer score = concurrentHashMap.get("小明");
                    int newScore = score + 1;
                    boolean replace = concurrentHashMap.replace("小明", score, newScore);
                    if (replace) {
                        break;
                    }
                }

//                Integer score = concurrentHashMap.get("小明");
//                int newScore = score + 1;
//                concurrentHashMap.put("小明", newScore);


            }



        }
    }


}
