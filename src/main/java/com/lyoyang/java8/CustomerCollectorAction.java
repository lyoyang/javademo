package com.lyoyang.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class CustomerCollectorAction {

    public static void main(String[] args) {
        Collector<String, List<String>, List<String>> myCollector = new ToListCollector();
        List<String> list = Arrays.asList("java", "stream", "php", "mongo", "flume", "redis");
//        List<String> collect = list.stream().filter(s -> s.length() > 3).collect(myCollector);
        List<String> collect = list.parallelStream().filter(s -> s.length() > 3).collect(myCollector);
        System.out.println(collect);
    }

}
