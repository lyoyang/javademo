package com.lyoyang.guava.cache;

import java.util.concurrent.TimeUnit;

/**
 * jvm params
 * -Xmx128M -Xms64M -XX:+PrintGCDetails
 */
public class SoftLRUCacheExample {


    public static void main(String[] args) throws InterruptedException {
        final SoftLRUCache<String, byte[]> cache = new SoftLRUCache<>(100);
//        final LinkedHashLRUCache<String, byte[]> cache = new LinkedHashLRUCache<>(100);
        for (int i = 0; i < 100; i++) {
            cache.put(String.valueOf(i), new byte[1024 * 1024 * 2]);
            TimeUnit.MILLISECONDS.sleep(600);
            System.out.println("the " + i + " is add to cache");
        }


    }

}
