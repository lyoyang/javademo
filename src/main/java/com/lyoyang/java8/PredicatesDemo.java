package com.lyoyang.java8;


import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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


    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(12, "green"), new Apple(13, "blue"),
                new Apple(23, "black"), new Apple(45, "red"));

        List<Apple> redApple = filterApple(apples, PredicatesDemo::isRedApple);
        System.out.println(redApple);

        List<Apple> moreTwentyOfWeight = filterApple(apples, (a) -> a.getWeight() > 20);
        System.out.println(moreTwentyOfWeight);


    }


    public static boolean isRedApple(Apple apple) {
        return apple.getColor().equals("red");
    }


    public static List<Apple> filterApple(List<Apple> apples, Predicate<Apple> p) {
        List<Apple> result = Lists.newArrayList();
        for (Apple apple : apples) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    @Data
    @ToString
    public static class Apple {
        private int weight;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }
    }


    interface ApplePredicate {
        boolean test();
    }
}
