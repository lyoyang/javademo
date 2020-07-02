package com.lyoyang.classinit;

import static java.lang.Thread.currentThread;

/**
 * 类加载
 */
public class Singleton {


    //1
    private static Singleton singleton = new Singleton();
    //2
    private static int x = 0;
    //3
    private static int y;



    public Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {
        return singleton;
    }

    /**
     * 输出结果为：0 1
     * 类初始化的时候，静态变量以及静态代码块是按代码的顺序执行的
     * 1执行完的时候 x = 1, y = 1
     * 执行2的时候又将x的值改为0，所以结果是 0 1
     * @param args
     */
    public static void main(String[] args) {
//        Singleton instance = Singleton.getInstance();
//        System.out.println(instance.x);
//        System.out.println(instance.y);
//        System.out.println(currentThread().getContextClassLoader());

        System.out.println(Dog.x);
    }


}
