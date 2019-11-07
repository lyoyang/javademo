package com.lyoyang.concurrent;

public class Singleton {

    public static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }

            }
        }
        return instance;
    }


    public void getInfo () {
        System.out.println(Thread.currentThread() + "--->" + instance.toString() + "this is a single Object...");
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            new Thread(() ->  Singleton.getInstance().getInfo()).start();
        }
    }





}
