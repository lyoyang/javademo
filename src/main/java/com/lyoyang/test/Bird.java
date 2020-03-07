package com.lyoyang.test;

public abstract class Bird implements Animal {

    @Override
    public void run() {
        System.out.println("birds can fly");
    }
}
