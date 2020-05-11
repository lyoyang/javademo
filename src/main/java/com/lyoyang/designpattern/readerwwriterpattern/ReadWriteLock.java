package com.lyoyang.designpattern.readerwwriterpattern;

public interface ReadWriteLock {

    Lock readLock();

    Lock writeLock();

    int getWritingWriters();

    int getWaitingWriters();

    int getWaitingReaders();

    static ReadWriteLock readWriterLock() {
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriterLock(boolean preferWriter) {
        return new ReadWriteLockImpl(preferWriter);
    }


    class ReadWriteLockImpl implements ReadWriteLock {

        private final Object MUTEX = new Object();

        private int writingWriters = 0;

        private int waitingWriters = 0;

        private int readingReaders = 0;

        private int waitingReaders = 0;

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
        public int getWritingWriters() {
            return this.writingWriters;
        }

        @Override
        public int getWaitingWriters() {
            return this.waitingWriters;
        }

        @Override
        public int getWaitingReaders() {
            return this.waitingReaders;
        }

        public int getReadingReaders() {
            return readingReaders;
        }

        private void incrementWritingWriters() {
            this.writingWriters++;
        }

        private void decrementWritingWriters() {
            this.writingWriters--;
        }

        private void incrementWaitingWriters() {
            this.waitingWriters++;
        }

        private void decrementWaitingWriters() {
            this.waitingWriters--;
        }


        private void incrementWaitingReaders() {
            this.waitingReaders++;
        }

        private void decrementWaitingReaders() {
            this.waitingReaders--;
        }

        private void incrementReadingReaders() {
            this.readingReaders++;
        }

        private void decrementReadingReaders() {
            this.readingReaders--;
        }

        public Object getMUTEX() {
            return MUTEX;
        }

        public boolean isPreferWriter() {
            return preferWriter;
        }

        public void changePrefer(boolean prefer) {
            this.preferWriter = prefer;
        }
    }

    class ReadLock implements Lock {
        private final ReadWriteLockImpl readWriteLock;

        public ReadLock(ReadWriteLockImpl readWriteLock) {
            this.readWriteLock = readWriteLock;
        }

        @Override
        public void lock() throws InterruptedException {
            synchronized (readWriteLock.getMUTEX()) {
                while (readWriteLock.getWritingWriters() > 0
                        || (readWriteLock.isPreferWriter() && readWriteLock.getWaitingWriters() > 0)) {
                    readWriteLock.getMUTEX().wait();
                }
                readWriteLock.incrementReadingReaders();
            }
        }

        @Override
        public void unlock() {
            synchronized (readWriteLock.getMUTEX()) {
                readWriteLock.decrementReadingReaders();
                readWriteLock.changePrefer(true);
                readWriteLock.getMUTEX().notifyAll();
            }
        }
    }


    class WriteLock implements Lock {
        private final ReadWriteLockImpl readWriteLock;

        public WriteLock(ReadWriteLockImpl readWriteLock) {
            this.readWriteLock = readWriteLock;
        }

        @Override
        public void lock() throws InterruptedException {
            synchronized (readWriteLock.getMUTEX()) {
                try {
                    readWriteLock.incrementWaitingWriters();
                    while (readWriteLock.getWritingWriters() > 0
                            || readWriteLock.getReadingReaders() > 0) {
                        readWriteLock.getMUTEX().wait();
                    }
                } finally {
                    readWriteLock.decrementWaitingWriters();
                }
                readWriteLock.incrementWritingWriters();
            }
        }

        @Override
        public void unlock() {
            synchronized (readWriteLock.getMUTEX()) {
                readWriteLock.decrementWritingWriters();
                readWriteLock.changePrefer(false);
                readWriteLock.getMUTEX().notifyAll();
            }
        }
    }


}
