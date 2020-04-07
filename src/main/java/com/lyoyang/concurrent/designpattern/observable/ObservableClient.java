package com.lyoyang.concurrent.designpattern.observable;

import java.util.concurrent.TimeUnit;

public class ObservableClient {

    public static void main(String[] args) {
        new ObservableThread<>(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the task finished");
            return null;
        }).start();
    }

}
