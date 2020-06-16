package com.lyoyang.concurrent.customthreadpool;

@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);

}
