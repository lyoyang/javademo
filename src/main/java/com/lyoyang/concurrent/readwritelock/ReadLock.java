package com.lyoyang.concurrent.readwritelock;

/**
 * @author: yangbing
 * @Date: 2020/4/24 18:35
 * @Description:
 */
public class ReadLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;


    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            while (readWriteLock.getReadingReaders() > 0 || (readWriteLock.isPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                readWriteLock.getMUTEX().wait();
            }
            readWriteLock.increaseReadingReaders();
        }
    }

    @Override
    public void unLock() {
        synchronized (readWriteLock.getMUTEX()) {
            readWriteLock.decreaseReadingReaders();
            readWriteLock.setPreferWriter(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
