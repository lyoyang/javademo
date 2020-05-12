package com.lyoyang.designpattern.singleton;

/**
 * 没有懒加载特性
 * 使用枚举
 * 枚举类型只能被实例化一次
 */
public enum Singleton7 {
    INSTANCE;

    Singleton7() {
        System.out.println("INSTANCE will be initialized");
    }

    public static void method() {

    }

    public static Singleton7 getInstance() {
        return INSTANCE;
    }

}
