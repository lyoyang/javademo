package com.lyoyang.test;

import com.lyoyang.entity.User;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class ReferenceDemo {


    public static void main(String[] args) {
        ReferenceQueue<User> referenceQueue = new ReferenceQueue<>();
        SoftReference<User> userSoftReference = new SoftReference<>(new User(), referenceQueue);
        System.gc();
        System.out.println("手动GC:" + userSoftReference.get());
        System.out.println("手动GC触发的队列：" + referenceQueue.poll());
        makeHeapNotEnough();
        System.out.println("通过堆内存不足触发GC:" + userSoftReference.get());
        System.out.println("通过堆内存不足触发GC:" + referenceQueue.poll());


    }


    private static void makeHeapNotEnough() {
        SoftReference softReference = new SoftReference(new byte[1024*1024*5]);
        byte[] bytes = new byte[1024 * 1024 * 5];
    }

}
