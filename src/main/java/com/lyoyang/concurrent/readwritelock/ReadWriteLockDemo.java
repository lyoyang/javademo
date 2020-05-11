package com.lyoyang.concurrent.readwritelock;

/**
 * @author: yangbing
 * @Date: 2020/4/24 18:59
 * @Description:
 */
public class ReadWriteLockDemo {


    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLocj = readWriteLock.writeLock();





    public static void main(String[] args) {

    }

}
