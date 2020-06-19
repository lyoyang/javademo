package com.lyoyang.cache;

import com.google.common.base.Preconditions;
import org.checkerframework.checker.units.qual.K;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author: Brian
 * @Date: 2020/6/19 14:18
 * @Description:
 */
public class LinkedListLRUCache<K, V> implements LRUCache<K, V> {

    private final Map<K, V> cache = new HashMap<>();

    private final LinkedList<K> linkedList = new LinkedList<>();

    private final int limit;

    public LinkedListLRUCache(int limit) {
        this.limit = limit;
    }

    @Override
    public void put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        if (linkedList.size() >= limit)  {
            cache.remove(linkedList.pollLast());
        }
        linkedList.addFirst(key);
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        boolean exists = linkedList.remove(key);
        if (!exists) {
            return null;
        }
        linkedList.addFirst(key);
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
        linkedList.remove(key);
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public int limit(int size) {
        return limit;
    }

    @Override
    public void clear() {
        this.linkedList.clear();
        this.cache.clear();
    }

    @Override
    public String toString() {
        return "LinkedListLRUCache{" +
                "cache=" + cache +
                '}';
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LinkedListLRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        cache.put("4", "4");
        cache.put("5", "5");
        cache.put("6", "6");
        System.out.println(cache.toString());
//        System.out.println(cache.get("3"));
//        System.out.println(cache.toString());
//        cache.put("4", "4");
//        System.out.println(cache.toString());
//        System.out.println(cache.get("2"));
//        cache.put("5", "5");
//        System.out.println(cache.toString());
    }


}
