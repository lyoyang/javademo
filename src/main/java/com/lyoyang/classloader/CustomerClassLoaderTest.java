package com.lyoyang.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomerClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        CustomerClassLoader customerClassLoader = new CustomerClassLoader();
        Class<?> aClass = customerClassLoader.loadClass("com.lyoyang.classloader.HelloWorld");
        Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);
        Method sayHello = aClass.getMethod("sysHello");
        String res = (String) sayHello.invoke(helloWorld);
        System.out.println(res);

    }

}
