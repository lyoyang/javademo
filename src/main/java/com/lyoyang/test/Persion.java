package com.lyoyang.test;

public abstract class Persion {

    public int a = 10;

    private int b = 30;

    protected String name = "bob";

    public void hello() {
        System.out.println("hello");
    }

    abstract void run();

    public void play() {
        System.out.println("abstract play...");
    }





}
