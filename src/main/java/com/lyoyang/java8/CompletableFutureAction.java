package com.lyoyang.java8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureAction {

    private static final Random RANDOM = new Random(System.currentTimeMillis());


    public static void main(String[] args) {
//       test1();
        test2();
//        test3();
//        test4();
    }






    public static void test1() {
        CompletableFuture<Object> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            double value = get();
            completableFuture.complete(value);

        }).start();
        System.out.println("=====continue=====");
        completableFuture.whenComplete((v,t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
        });
    }


    /**
     * 线程池创建的线程默认为非守护线程
     */
    public static void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r-> {
            Thread thread = new Thread(r);
//            thread.setDaemon(true);
            return thread;
        });

        CompletableFuture.supplyAsync(CompletableFutureAction::get, executorService)
                .whenComplete((v, t) -> {
                   Optional.of(v).ifPresent(System.out::println);
                   Optional.of(t).ifPresent(x -> x.printStackTrace());
                });

        System.out.println("=====continue======");
    }


    public static void test3() {
        ExecutorService executorService = Executors.newFixedThreadPool(2, r-> {
            Thread thread = new Thread(r);
//            thread.setDaemon(false);
            return thread;
        });
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> result = list.stream().map(i -> CompletableFuture.supplyAsync(CompletableFutureAction::get, executorService))
                .map(future -> future.thenApply(CompletableFutureAction::multiply))
                .map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(result);
    }


    public static void test4() {
        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> Integer.sum(i, 10))
                .thenAccept(System.out::println);


        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> 10*i))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2), (v1,v2) -> v1 + v2)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2), (r1, r2) -> {
                    System.out.println(r1);
                    System.out.println(r2);
                    System.out.println(r1 + r2);
                }).thenRun(System.out::println);


        CompletableFuture.supplyAsync(() -> {
            System.out.println("i am future 1");
            return 1;
        }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("i am future 2");
            return 2;
        }), () -> System.out.println("done"));


        CompletableFuture.supplyAsync(() -> {
            System.out.println("i am future 1");
            return 1;
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("i am future 2");
            return 2;
        }), v -> v*10)
                .thenAccept(System.out::println);


        CompletableFuture.supplyAsync(() -> {
            System.out.println("i am future 1");
            return 1;
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("i am future 2");
            return 2;
        }), System.out::println);

        CompletableFuture.supplyAsync(() -> {
            System.out.println("i am future 1");
            return 1;
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("i am future 2");
            return 2;
        }), () -> System.out.println("done"));


        List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4, 5).stream().map(i -> CompletableFuture.supplyAsync(CompletableFutureAction::get))
                .collect(Collectors.toList());
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> {
                    System.out.println("have done");
                });

        CompletableFuture.anyOf(collect.toArray(new CompletableFuture[collect.size()]))
                .thenRun(() -> {
                    System.out.println("have done");
                });

    }


    private static double get() {
        try {
            Thread.sleep(RANDOM.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }

    private static double multiply(double value) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value * 10d;
    }

}
