package com.lyoyang.designpattern.readerwwriterpattern;


import static java.lang.Thread.currentThread;

public class ReadWriteLockTest {

    private static final String text = "sdfmvcnksdpjfghncbgjdfgngfhj";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(50);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < text.length(); j++) {
                        char c = text.charAt(j);
                        shareData.write(c);
                        System.out.println(currentThread() + " write " + c);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
//                while (true) {
                    try {
                        System.out.println(currentThread() + " read " + new String(shareData.read()));

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                }
            }).start();
        }
    }

}
