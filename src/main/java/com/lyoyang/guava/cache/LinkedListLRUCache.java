package com.lyoyang.guava.cache;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.LinkedList;

public class LinkedListLRUCache<K, V> implements LRUCache<K,V> {

    private final int limit;
    private final LinkedList<K> linkedList = new LinkedList<>();

    private final HashMap<K, V> cache = new HashMap<>();

    public LinkedListLRUCache(int limit) {
        this.limit = limit;
    }

    @Override
    public void put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        if (linkedList.size() >= limit) {
            linkedList.removeFirst();
            cache.remove(key);
        }
        linkedList.addLast(key);
        cache.put(key, value);
    }

    @Override
    public V get(K key) {
        boolean exists = linkedList.remove(key);
        if (!exists) {
            return null;
        }
        linkedList.addLast(key);
        return cache.get(key);
    }

    @Override
    public void remove(K key) {
        boolean exists = linkedList.remove(key);
        if (exists) {
            cache.remove(key);
        }
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public void clear() {
        linkedList.clear();
        cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (K k : linkedList) {
            builder.append("k=").append(cache.get(k)).append(";");
        }
        builder.append("}");
        return builder.toString();
    }
}
