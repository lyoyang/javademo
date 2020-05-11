package com.lyoyang.concurrent.readwritelock;

/**
 * @author: yangbing
 * @Date: 2020/4/24 18:21
 * @Description:
 */
public interface Lock {
    void lock() throws InterruptedException;

    void unLock();
}
