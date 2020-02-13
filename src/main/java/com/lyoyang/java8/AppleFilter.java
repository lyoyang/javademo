package com.lyoyang.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleFilter {

    @FunctionalInterface
    public interface FilterApple {
        boolean filter(Apple apple);
    }


    public static List<Apple> findApple(List<Apple> list, FilterApple filterApple) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : list) {
            if (filterApple.filter(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }


    public static class YellowLess150WeightFilter implements FilterApple {

        @Override
        public boolean filter(Apple apple) {
            return "yellow".equals(apple.getColor()) && apple.getWeight() < 150;
        }
    }






    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("yellow", 130), new Apple("yellow", 230), new Apple("green", 123));
        List<Apple> filterApple = findApple(apples, new YellowLess150WeightFilter());
        assert filterApple.size() == 1 : "not right";
        System.out.println(filterApple);
        List<Apple> green = findApple(apples, apple -> apple.getColor().equals("green"));
        System.out.println(green);


    }



}
