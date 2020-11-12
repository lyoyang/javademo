package com.lyoyang.concurrent.readwritelock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: yangbing
 * @Date: 2020/4/24 18:59
 * @Description:
 */
public class ReadWriteLockDemo {


    private final ReadWriteLock readWriteLock = ReadWriteLock.readWriteLock();

    private final Lock readLock = readWriteLock.readLock();

    private final Lock writeLock = readWriteLock.writeLock();

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(5);

    private List<String> list = new ArrayList<>(10);



    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
//        for (int i = 0; i < 5; i++) {
//            EXECUTOR_SERVICE.execute(readWriteLockDemo::read);
//        }
        List<String> strings = Arrays.asList("java", "php", "scala", "golang");
        for (int i = 0; i < strings.size(); i++) {
            final String s = strings.get(i);
            EXECUTOR_SERVICE.execute(() ->
                readWriteLockDemo.write(s)
            );
        }
    }


    public void read() {
        try {
            readLock.lock();
            System.out.println(list.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unLock();
        }
    }


    public void write(String string) {
        try {
            writeLock.lock();
            for (int i = 0; i < list.size(); i++) {
                list.set(i, string);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unLock();
        }
    }


    class Writer implements Runnable {
        private String str;

        private Lock lock;

        public Writer(String str, Lock lock) {
            this.str = str;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " set list:" + str);
                for (int i = 0; i < list.size(); i++) {
                    list.set(i, str);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unLock();
            }

        }
    }

    class Reader implements Runnable {

        private Lock lock;

        public Reader(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " list:" + list.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unLock();
            }

        }
    }


}
