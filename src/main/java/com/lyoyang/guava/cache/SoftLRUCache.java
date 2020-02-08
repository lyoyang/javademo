package com.lyoyang.guava.cache;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 软引用实现缓存
 * @param <K>
 * @param <V>
 * the class is not thread-safe
 */
public class SoftLRUCache<K, V> implements LRUCache<K, V> {


    private final int limit;

    private InteralCache<K, V> cache;

    public SoftLRUCache(int limit) {
        this.limit = limit;
        this.cache = new InteralCache<>(limit);
    }

    private static class InteralCache<K, V> extends LinkedHashMap<K, SoftReference<V>> {
        private final int limit;

        public InteralCache(int limit) {
            super(16, 0.75f);
            this.limit = limit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, SoftReference<V>> eldest) {
            return this.size() > limit;
        }
    }


    @Override
    public void put(K key, V value) {
        this.cache.put(key, new SoftReference<>(value));
    }

    @Override
    public V get(K key) {
        SoftReference<V> reference = this.cache.get(key);
        if (reference.get() == null) return null;
        return reference.get();
    }

    @Override
    public void remove(K key) {
        this.cache.remove(key);
    }

    @Override
    public int size() {
        return this.cache.size();
    }

    @Override
    public void clear() {
        this.cache.clear();
    }

    @Override
    public int limit() {
        return this.limit;
    }
}
