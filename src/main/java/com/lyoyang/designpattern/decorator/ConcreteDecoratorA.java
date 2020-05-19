package com.lyoyang.designpattern.decorator;

/**
 * @author: Brian
 * @Date: 2020/5/19 11:36
 * @Description:
 */
public class ConcreteDecoratorA extends Decorator {

    @Override
    public void operation() {
        super.operation();
        System.out.println("具体装饰者A");
    }
}
