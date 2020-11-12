package com.lyoyang.concurrent.customthreadpool;

public class RunnableDenyException extends RuntimeException {

    public RunnableDenyException(String msg) {
        super(msg);
    }
}
