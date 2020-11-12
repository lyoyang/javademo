package com.lyoyang.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: Brian
 * @Date: 2020/6/19 13:55
 * @Description:
 */
public class LinkedHashLRUCache<K, V> implements LRUCache<K, V> {


    private final int limit;

    private final InternalHashMap<K, V> internalHashMap;


    public LinkedHashLRUCache(int limit) {
        this.limit = limit;
        internalHashMap = new InternalHashMap<>(limit);
    }

    private static class InternalHashMap<K, V> extends LinkedHashMap<K, V> {
        private final int limit;

        public InternalHashMap(int limit) {
            super(16, 0.75f, true);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }
    }


    @Override
    public void put(K key, V value) {
        internalHashMap.put(key, value);
    }

    @Override
    public V get(K key) {
        return internalHashMap.get(key);
    }

    @Override
    public void remove(K key) {
        internalHashMap.remove(key);
    }

    @Override
    public int size() {
        return internalHashMap.size();
    }

    @Override
    public int limit(int size) {
        return this.limit;
    }

    @Override
    public void clear() {
        this.internalHashMap.clear();
    }

    @Override
    public String toString() {
        return "LinkedHashLRUCache{" +
                "limit=" + limit +
                ", internalHashMap=" + internalHashMap +
                '}';
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LinkedHashLRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
//        cache.put("4", "4");
        System.out.println(cache.toString());
        System.out.println(cache.get("3"));
        System.out.println(cache.toString());
        cache.put("4", "4");
        System.out.println(cache.toString());
        System.out.println(cache.get("2"));
        cache.put("5", "5");
        System.out.println(cache.toString());


    }


}
