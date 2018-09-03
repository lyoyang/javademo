package com.lyoyang.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 */
public class CglibProxxy {
    public static void main(String[] args) {
        //字节码增强器
        Enhancer enhancer = new Enhancer();
        //设置别代理类为父类
        enhancer.setSuperclass(User.class);
        //设置回调
        enhancer.setCallback(new UserInterceptor());
        User user = (User) enhancer.create();
        user.eat("banana");
    }
}


/**
 * 被代理类
 */
class User {
    public void eat(String str) {
        System.out.println("i want to eat " + str);
    }
}

class UserInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("proxy start...");
        Object res = methodProxy.invokeSuper(o, objects);
        System.out.println("proxy end.....");
        return res;
    }
}