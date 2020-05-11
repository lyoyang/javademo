package com.lyoyang.designpattern.singleton;



/**
 * 懒汉式 + 同步
 * 效率不高
 */

public class Singleton2 {

    private static Singleton2 instance = null;

    private Singleton2() {
    }

    public synchronized Singleton2 getInstance() {
            if (instance == null)
                instance = new Singleton2();
        return instance;
    }



}
