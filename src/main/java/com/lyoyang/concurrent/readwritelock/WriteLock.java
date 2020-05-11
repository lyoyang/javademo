package com.lyoyang.concurrent.readwritelock;

/**
 * @author: yangbing
 * @Date: 2020/4/24 18:52
 * @Description:
 */
public class WriteLock implements Lock {

    private final ReadWriteLockImpl readWriteLock;


    public WriteLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()) {
            try {
                readWriteLock.increaseWaitingWriters();
                if (readWriteLock.getReadingReaders() > 0 || readWriteLock.getWritingWriters() > 0) {
                    readWriteLock.getMUTEX().wait();
                }
            } finally {
                readWriteLock.decreaseWaitingWriters();
            }
            readWriteLock.increaseWritingWriters();
        }
    }

    @Override
    public void unLock() {
        synchronized (readWriteLock.getMUTEX()) {
            readWriteLock.decreaseWritingWriters();
            readWriteLock.setPreferWriter(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
