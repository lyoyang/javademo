package com.lyoyang.designpattern.singleton;


/**
 * 懒汉式 + Double Check
 * 可能会引起空指针异常
 */
public class Singleton3 {

    private static Singleton3 instance = null;

    private Singleton3() {
    }


    public static Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }


}
