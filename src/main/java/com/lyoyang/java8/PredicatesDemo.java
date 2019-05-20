package com.lyoyang.java8;


import org.junit.Test;

import java.util.function.Predicate;

/**
 * @Auther: yangbing
 * @Date: 2019/3/27 10:52
 * @Description:
 */
public class PredicatesDemo {

    @Test
    public void test() {
        Predicate<String> predicate = (s) -> s.length()>0;
        boolean jim = predicate.test("jim");
        System.out.println(jim);
        boolean bob = predicate.negate().test("bob");
        System.out.println(bob);

    }



}
