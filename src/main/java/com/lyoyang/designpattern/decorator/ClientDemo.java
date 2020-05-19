package com.lyoyang.designpattern.decorator;

/**
 * @author: Brian
 * @Date: 2020/5/19 11:39
 * @Description:
 */
public class ClientDemo {

    public static void main(String[] args) {
        ConcreteComponent concreteComponent = new ConcreteComponent();
        ConcreteDecoratorA concreteDecoratorA = new ConcreteDecoratorA();
        ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB();
        concreteDecoratorA.setComponent(concreteComponent);
        concreteDecoratorB.setComponent(concreteDecoratorA);
        concreteDecoratorB.operation();
    }

}
