package com.lyoyang.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DirectMemoryOOM {

    private static final int _1Mb = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field declaredField = Unsafe.class.getDeclaredFields()[0];
        declaredField.setAccessible(true);
        Unsafe unsafe = (Unsafe)declaredField.get(null);
        while (true) {
            unsafe.allocateMemory(_1Mb);
        }
     }

}
