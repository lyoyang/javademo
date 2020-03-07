package com.lyoyang.java8;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector implements Collector<String, List<String>, List<String>> {

    private void log(String log) {
        System.out.println(log);
    }


    @Override
    public Supplier<List<String>> supplier() {
        log("supplier");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<String>, String> accumulator() {
        log("accumulator");
        return List::add;
    }

    @Override
    public BinaryOperator<List<String>> combiner() {
        log("combiner");
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<String>, List<String>> finisher() {
        log("finisher");
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        log("characteristics");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
    }
}
