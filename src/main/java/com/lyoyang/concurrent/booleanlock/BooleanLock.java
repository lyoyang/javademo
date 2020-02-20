package com.lyoyang.concurrent.booleanlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {


    private Thread currentThread;

    private boolean locked;

    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                blockedList.add(currentThread());
                this.wait();
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();

        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills < 0) {
                lock();
            }
            long remainMills = mills;
            long endMills = currentTimeMillis() + mills;
            while (locked) {
                if (remainMills <= 0) {
                    throw new TimeoutException("can not get the lock during " + mills);
                }
                if (!blockedList.contains(currentThread())) {
                    blockedList.add(currentThread());
                    this.wait();
                    remainMills = endMills - currentTimeMillis();
                }
            }
            blockedList.remove(currentThread());
            locked = true;
            this.currentThread = currentThread();

        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (this.currentThread == currentThread()) {}
            this.locked = false;
            Optional.of(currentThread.getName() + " release the lock")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
