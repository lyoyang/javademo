package com.lyoyang.java8;


import org.junit.Test;

public class LambaDemo {
    public static void main(String[] args) {
        LambaDemo lamba = new LambaDemo();
        MethodOperation p1 = (int a, int b) -> a + b;
        MethodOperation p2 = (a,b) -> a - b;
        MethodOperation p3 = (int a, int b) -> {return a*b;};
        MethodOperation p4 = (int a, int b) -> a/b;
        System.out.println("10+5=" + lamba.operate(10,5, p1));
        System.out.println("10-5=" + lamba.operate(10,5, p2));
        System.out.println("10*5=" + lamba.operate(10,5, p3));
        System.out.println("10/5=" + lamba.operate(10,5, p4));
        GreetingService greet = message -> System.out.println("hello " + message);
        GreetingService greet2 = (message) -> System.out.println("hello-->" + message);
        greet.sayMessage("jim");
        greet2.sayMessage("bruce");
    }

    @Test
    public void test() {
        Convert<String,Integer> convert = (from) -> Integer.valueOf(from);
        Integer res = convert.convert("123");
        System.out.println(res);
        Convert<String,Integer> c2 = Integer::valueOf;
        Integer rr = c2.convert("345");
        System.out.println(rr);

    }

    interface  MethodOperation{
        int operation(int a, int b);
    }

    interface  GreetingService{
        void sayMessage(String msg);
    }

    private int operate(int a, int b, MethodOperation operation){
        return  operation.operation(a,b);
    }
}
