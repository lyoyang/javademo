package com.lyoyang.spi;

/**
 * @author: yangbing
 * @Date: 2020/2/13 16:44
 * @Description:
 */
public class HelloService1Impl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("this is hello1 service...");
    }
}
