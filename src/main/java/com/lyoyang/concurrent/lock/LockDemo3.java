package com.lyoyang.concurrent.lock;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：读跟写不能共存，写-写不能共存
 */
public class LockDemo3 {
    static Map<String, Object> map = Maps.newHashMap();
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock r = lock.readLock();
    static Lock w = lock.writeLock();

    public static final Object get(String key) {
        r.lock();
        try {
            System.out.println("正在做读的操作，key:" + key + "开始");
            Thread.sleep(100);
            Object o = map.get(key);
            System.out.println("读操作完毕，key：" + key);
            System.out.println();
            return o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            r.unlock();
        }
        return key;
    }

    public static final Object put(String key, Object value) {
        w.lock();
        try {
            System.out.println("正在做写的操作,key:" + key + ",value:" + value + "开始.");
            Thread.sleep(100);
            Object object = map.put(key, value);
            System.out.println("正在做写的操作,key:" + key + ",value:" + value + "结束.");
            System.out.println();
            return object;
        } catch (InterruptedException e) {

        } finally {
            w.unlock();
        }
        return value;
    }

    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    LockDemo3.put(i+ "", i+"");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    System.out.println(LockDemo3.get(i + ""));
                }
            }
        }).start();
    }

}
