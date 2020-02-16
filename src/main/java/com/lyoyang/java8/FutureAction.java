package com.lyoyang.java8;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FutureAction {


    public static void main(String[] args) {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "I am Finished.";
            } catch (InterruptedException e) {
                return "I am Error";
            }
        });
        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable throwable) {
                System.out.println("error");
                throwable.printStackTrace();
            }
        });

        System.out.println(future.get());
        System.out.println("do something continue.");
    }




    private static <T> Future<T> invoke(Callable<T> callable) {
        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Future<T> future = new Future<T>() {

            private Completable<T> completable;

            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return atomicBoolean.get();
            }

            @Override
            public void setCompletable(Completable completable) {
                this.completable = completable;
            }

            @Override
            public Completable getCompletable() {
                return completable;
            }
        };
        new Thread(() -> {
            try {
                T value = callable.action();
                reference.set(value);
                atomicBoolean.set(true);
                if (future.getCompletable() != null) {
                    future.getCompletable().complete(value);
                }
            } catch (Exception e) {
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(e);
                }
            }
        }).start();
        return future;
    }



    private interface Callable<T> {
        T action();
    }


    private interface Future<T> {
        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }


    private interface Completable<T> {
        void complete(T t);

        void exception(Throwable throwable);
    }







}
