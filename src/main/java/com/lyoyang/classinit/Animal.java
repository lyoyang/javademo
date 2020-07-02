package com.lyoyang.classinit;

/**
 * @author: Brian
 * @Date: 2020/7/1 14:37
 * @Description:
 */
public class Animal {


    static {
        System.out.println("animal init...");
    }

    public static int x = 1;

    public Animal() {
        System.out.println("animal contructor...");
    }
}
