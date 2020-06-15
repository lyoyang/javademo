package com.lyoyang.concurrent.customthreadpool;

public interface RunnableQueue {

    void offer(Runnable runnable);

    Runnable take() throws InterruptedException;

    int size();

}
