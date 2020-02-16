package com.lyoyang.spi;

import java.util.ServiceLoader;

/**
 * @author: yangbing
 * @Date: 2020/2/13 16:47
 * @Description:
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<HelloService> load = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : load) {
            helloService.sayHello();
        }
    }
}
