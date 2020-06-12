package com.lyoyang.concurrent.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

public class StampedLockTest {

    private static final StampedLock lock = new StampedLock();

    private static final List<Long> DATA = new ArrayList<>();


    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

    }

    private static void read() {
        long stamped = -1;
        try {
            stamped = lock.readLock();
            Optional.of(DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", "")))
                    .ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }
    }


    private static void write() {
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }

}
