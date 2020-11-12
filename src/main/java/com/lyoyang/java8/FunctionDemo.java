package com.lyoyang.java8;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author: Brian
 * @Date: 2020/5/22 16:27
 * @Description:
 */
public class FunctionDemo {



    public static void main(String[] args) {
        Optional.of(adder(13, a -> Integer.toString(a))).ifPresent(System.out::println);
    }


    public static String adder(Integer a, Function<Integer, String> function) {
        return function.apply(a);
    }

}
