package com.lyoyang.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnSafeTest {


    public static void main(String[] args) throws Exception {

        Simple simple1 = Simple.class.newInstance();
        System.out.println(simple1.getL());

        Class.forName("com.lyoyang.concurrent.UnSafeTest$Simple");

        Unsafe unSafe = getUnSafe();
        Simple simple  = (Simple) unSafe.allocateInstance(Simple.class);
        System.out.println(simple.getL());
    }



    static class Simple {
        private int l = 0;

        public Simple() {
            l = 1;
            System.out.println("============");
        }

        public int getL() {
            return l;
        }
    }


    private static Unsafe getUnSafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
