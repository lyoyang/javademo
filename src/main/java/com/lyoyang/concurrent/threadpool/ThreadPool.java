package com.lyoyang.concurrent.threadpool;

public interface ThreadPool {

    void execute(Runnable runnable);

    void shutDown();

    int getInitSize();

    int getMaxSize();

    int getCoreSize();

    int getQueueSize();

    int getActiveCount();

    boolean isShutDown();

}
