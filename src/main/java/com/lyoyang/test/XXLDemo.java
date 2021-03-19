package com.lyoyang.test;

public class XXLDemo {


    private String name;

    public XXLDemo(String name) {
        this.name = name;
        System.out.println("构造器");
    }

    static {
        System.out.println("static block");
    }

    public static void sayHello() {
        System.out.println("hello");
    }

    public void hello() {
        System.out.println("default hello");
    }

}
