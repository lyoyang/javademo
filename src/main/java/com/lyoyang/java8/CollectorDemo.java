package com.lyoyang.java8;

import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CollectorDemo {

    private static final List<Apple> apples = Arrays.asList(new Apple("red", 10L),
            new Apple("greeen", 20L),
            new Apple("blue", 30L),
            new Apple("red", 13L),
            new Apple("green", 17L));
    private static final List<Apple> appList = Arrays.asList(new Apple("Red", 12L),
            new Apple("Blue", 13L),
            new Apple("Green", 20L),
            new Apple("Yellow", 15L));


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

        Optional<List<Apple>> collect = Optional.of(apples.stream().collect(collectingAndThen(toList(), Collections::unmodifiableList)));

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


    @Test
    public void testCollectGroub() {
        Optional.ofNullable(apples.stream().collect(groupingByConcurrent(Apple::getColor))).ifPresent(System.out::println);
    }


    @Test
    public void testCollectJoin() {
        Optional.of(apples.stream().map(Apple::getColor).collect(joining())).ifPresent(System.out::println);
        Optional.of(apples.stream().map(Apple::getColor).collect(joining("-"))).ifPresent(System.out::println);
        Optional.of(apples.stream().map(Apple::getColor).collect(joining("-", "Colors[", "]"))).ifPresent(System.out::println);
    }


    @Test
    public void testCollectMapping() {
        Optional.of(apples.stream().collect(mapping(Apple::getColor, joining("-")))).ifPresent(System.out::println);
    }

    @Test
    public void testCollectMaxByAndMin() {
        apples.stream().collect(maxBy(Comparator.comparingLong(Apple::getWeight))).ifPresent(System.out::println);
        apples.stream().collect(minBy(Comparator.comparingLong(Apple::getWeight))).ifPresent(System.out::println);
    }

    @Test
    public void testPartitioningWithPredict() {
        Optional.of(apples.stream().collect(partitioningBy(apple -> apple.getColor().equals("red")))).ifPresent(System.out::println);
        Optional.of(apples.stream().collect(partitioningBy(apple -> apple.getColor().equals("red"), Collectors.averagingDouble(Apple::getWeight)))).ifPresent(System.out::println);
    }


    @Test
    public void testReduce() {
        apples.stream().collect(reducing(BinaryOperator.maxBy(Comparator.comparingLong(Apple::getWeight))))
                .ifPresent(System.out::println);
        Optional.of(apples.stream().map(Apple::getWeight).collect(reducing(0, (d1, d2)-> d1.longValue() + d2.longValue()))).ifPresent(System.out::println);
        Optional.of(apples.stream().collect(reducing(0, Apple::getWeight, (d1, d2)-> d1.longValue() + d2.longValue()))).ifPresent(System.out::println);
    }












}
