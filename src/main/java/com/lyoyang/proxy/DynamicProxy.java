package com.lyoyang.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理demo
 */
public class DynamicProxy {

    public static void main(String[] args) {
        Dog dog = new Dog();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(dog);
        Animal proxyDog = (Animal) Proxy.newProxyInstance(Dog.class.getClassLoader(), dog.getClass().getInterfaces(), myInvocationHandler);
        proxyDog.run();
    }


}


interface Animal {
    void run();
}

class Dog implements Animal {

    @Override
    public void run() {
        System.out.println("this dog is running....");
    }
}

class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoking start....");
        Object result = method.invoke(target, args);
        System.out.println("invoking end.....");
        return result;
    }
}