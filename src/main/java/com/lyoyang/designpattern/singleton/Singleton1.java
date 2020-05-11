package com.lyoyang.designpattern.singleton;

/**
 * 懒汉式
 * 使用与当线程情况，多线程会发生并发问题
 */
public class Singleton1 {


    private static Singleton1 instance = null;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }


}
