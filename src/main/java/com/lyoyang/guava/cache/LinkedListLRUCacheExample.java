package com.lyoyang.guava.cache;

public class LinkedListLRUCacheExample {


    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LinkedListLRUCache<>(3);
        lruCache.put("1", "1");
        lruCache.put("2", "2");
        lruCache.put("3", "3");
        System.out.println(lruCache);
        lruCache.put("4", "4");
        System.out.println(lruCache.get("2"));
        System.out.println(lruCache);
        System.out.println(lruCache.get("3"));
        System.out.println(lruCache);
    }
}
