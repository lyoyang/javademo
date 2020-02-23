package com.lyoyang.classinit;

import static java.lang.Thread.currentThread;

public class Singleton {



    private static Singleton singleton = new Singleton();
    private static int x = 0;

    private static int y;



    public Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance.x);
        System.out.println(instance.y);
        System.out.println(currentThread().getContextClassLoader());
    }


}
