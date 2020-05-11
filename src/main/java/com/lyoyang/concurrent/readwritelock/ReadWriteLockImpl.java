package com.lyoyang.concurrent.readwritelock;

/**
 * @author: yangbing
 * @Date: 2020/4/24 18:27
 * @Description:
 */
public class ReadWriteLockImpl implements ReadWriteLock {

    private final Object MUTEX = new Object();

    private int waitingWriters = 0;

    private int writingWriters = 0;

    private int readingReaders = 0;

    private boolean preferWriter;


    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }

    @Override
    public Lock writeLock() {
        return new WriteLock(this);
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }


    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    void increaseWritingWriters() {
        this.writingWriters++;
    }

    void decreaseWritingWriters() {
        this.writingWriters--;
    }

    void decreaseWaitingWriters() {
        this.waitingWriters--;
    }


    void increaseWaitingWriters() {
        this.waitingWriters++;
    }


    void increaseReadingReaders() {
        this.readingReaders++;
    }

    void decreaseReadingReaders() {
        this.readingReaders--;
    }

    public Object getMUTEX() {
        return MUTEX;
    }

    public boolean isPreferWriter() {
        return preferWriter;
    }

    public void setPreferWriter(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }
}
