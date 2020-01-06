package com.lyoyang.java8;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorDemo {

    private static final List<Apple> apples = Arrays.asList(new Apple("red", 10L),
            new Apple("greeen", 20L),
            new Apple("blue", 30L),
            new Apple("red", 13L),
            new Apple("green", 17L));

    public static void main(String[] args) {
        testGroupByFunctionAndCollector();
        testCollectAndThen();
    }




    private static Map<String, List<Apple>> groupByColor(List<Apple> apples){
        Map<String, List<Apple>> map = new HashMap<>();
        for (Apple apple : apples) {
            List<Apple> sepcApple = map.get(apple.getColor());
            if (sepcApple != null) {
                sepcApple.add(apple);
            } else {
                List<Apple> newList = new ArrayList<>();
                newList.add(apple);
            }
        }
        return map;
    }


    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples){
        Map<String, List<Apple>> map = new HashMap<>();
        apples.stream().forEach(a -> {
            List<Apple> appList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                list.add(a);
                return list;
            });
            appList.add(a);
        });
        return map;
    }

    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples){
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }


    private static void testGroupByFunctionAndCollector() {
        System.out.println("testGroupByFunctionAndCollector");
        Optional.ofNullable(apples.stream().collect(Collectors.groupingBy(Apple::getColor, Collectors.averagingDouble(Apple::getWeight))))
                .ifPresent(System.out::println);
    }



    private static void testCollectAndThen() {
        Optional.of(apples.stream().collect(Collectors.collectingAndThen(Collectors.averagingDouble(Apple::getWeight), a-> "The average is :" + a)))
                .ifPresent(System.out::println);
    }






}
