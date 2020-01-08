package com.lyoyang.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorDemo {

    private static final List<Apple> appList = Arrays.asList(new Apple("Red", 12L),
            new Apple("Blue", 13L),
            new Apple("Green", 20L),
            new Apple("Yellow", 15L));

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


    @Test
    public void testCollect() {
        Optional.of(appList.stream().collect(Collectors.counting())).ifPresent(System.out::println);
        Optional.of(appList.stream().count()).ifPresent(System.out::println);
        appList.stream().collect(maxBy(Comparator.comparing(Apple::getWeight))).ifPresent(System.out::println);
        Optional.of(appList.stream().collect(summarizingLong(Apple::getWeight))).ifPresent( s ->
                System.out.println("count=" + s.getCount() + ",sum=" + s.getSum() + ",average=" + s.getAverage()));
        Optional.of(appList.stream().collect(averagingLong(Apple::getWeight))).ifPresent(System.out::println);
        Optional.of(appList.stream().map(Apple::getColor).collect(joining())).ifPresent(System.out::println);
    }




}
