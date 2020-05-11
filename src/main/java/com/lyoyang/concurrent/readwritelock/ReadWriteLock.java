package com.lyoyang.concurrent.readwritelock;

/**
 * @author: yangbing
 * @Date: 2020/4/24 18:22
 * @Description:
 */
public interface ReadWriteLock {

    Lock readLock();

    Lock writeLock();

    int getWaitingWriters();

    int getWritingWriters();

    int getReadingReaders();


    static ReadWriteLock readWriteLock() {
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }
}
