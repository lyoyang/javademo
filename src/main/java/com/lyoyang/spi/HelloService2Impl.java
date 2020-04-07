package com.lyoyang.spi;

/**
 * @author: yangbing
 * @Date: 2020/2/13 16:45
 * @Description:
 */
public class HelloService2Impl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("this is hello2 service...");
    }
}
