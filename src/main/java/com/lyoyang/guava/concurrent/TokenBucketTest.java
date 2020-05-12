package com.lyoyang.guava.concurrent;

public class TokenBucketTest {

    public static void main(String[] args) {
        final TokenBuckt tokenBuckt = new TokenBuckt();
        for (int i = 0; i < 200; i++) {
            new Thread(tokenBuckt::buy).start();
        }
    }

}
