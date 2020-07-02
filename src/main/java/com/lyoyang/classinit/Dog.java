package com.lyoyang.classinit;

/**
 * @author: Brian
 * @Date: 2020/7/1 14:38
 * @Description:
 */
public class Dog extends Animal {


    static {
        System.out.println("dog init...");
    }

    public static int a = 2;

    public final static String s = "wang";

    public Dog() {
        System.out.println("dog contructor...");
    }

    public static void main(String[] args) {
        System.out.println(Dog.s);
    }




}
