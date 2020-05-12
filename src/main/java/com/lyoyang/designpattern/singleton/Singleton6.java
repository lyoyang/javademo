package com.lyoyang.designpattern.singleton;

/**
 * 按需创建
 */

public class Singleton6 {

    private Singleton6() {
    }

    public static Singleton6 getInstance() {
        return InnerClass.instance;
    }

    private static class InnerClass {
        private static Singleton6 instance = new Singleton6();
    }

}
