package com.lyoyang.designpattern.readerwwriterpattern;

public interface Lock {

    void lock() throws InterruptedException;

    void unlock();

}
