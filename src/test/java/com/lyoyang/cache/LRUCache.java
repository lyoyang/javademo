package com.lyoyang.cache;

/**
 * @author: Brian
 * @Date: 2020/6/19 13:52
 * @Description:
 */
public interface LRUCache<K,V> {

    void put(K key, V value);

    V get(K key);

    void remove(K key);

    int size();

    int limit(int size);

    void clear();

}
