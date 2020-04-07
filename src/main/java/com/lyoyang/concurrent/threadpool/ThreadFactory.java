package com.lyoyang.concurrent.threadpool;

@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);

}
