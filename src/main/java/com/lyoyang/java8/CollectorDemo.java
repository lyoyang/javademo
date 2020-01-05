package com.lyoyang.java8;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorDemo {

    public static void main(String[] args) {

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





}
