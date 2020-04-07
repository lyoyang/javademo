package com.lyoyang.concurrent.threadpool;

public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String msg) {
        super(msg);
    }
}
