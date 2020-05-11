package com.lyoyang.guava.concurrent;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.*;

public class ListenableFutureExample {

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        future();
        listenFuture();
        completeableFuture();
        System.out.println("main thread");
    }


    private static void future() throws ExecutionException, InterruptedException {
        Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        Integer res = future.get();
        System.out.println(res);

    }

    public static void listenFuture() {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executor);
        ListenableFuture<Integer> future = listeningExecutorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 20;
        });
        future.addListener(() -> System.out.println("task finished"), executor);
        Futures.addCallback(future, new MyCallback(), executor);
    }

    static class MyCallback implements FutureCallback<Integer> {
        @Override
        public void onSuccess(@Nullable Integer result) {
            System.out.println("I am finished, the result is " + result);
        }

        @Override
        public void onFailure(Throwable t) {
            t.printStackTrace();
        }
    }


    public static void completeableFuture() {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 30;
        }, executor).whenComplete((v, t) -> System.out.println("I am finished,the result is " + v));
    }




}
