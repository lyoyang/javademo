package com.lyoyang.concurrent.lock;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static final Lock rtl = new ReentrantLock(true);

    public int count;

    private static final Lock l1 = new ReentrantLock();
    private static final Lock l2 = new ReentrantLock();



    public static void main(String[] args) throws InterruptedException {
//        for (int i = 1; i <= 5; i++) {
//            new Thread(new ThreadDemo(i)).start();
//        }

//        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue(2);
//        for (int i = 0; i < 10; i++) {
//            int data = i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        myBlockingQueue.enqueue(data);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
//
//
//        for (int i = 0; i < 10; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Integer data = myBlockingQueue.dequeue();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }

        Thread thread1 = new Thread(new DeadLockDemo(l1, l2));
        Thread thread2 = new Thread(new DeadLockDemo(l2, l1));
        thread2.start();
        thread1.start();
        thread2.interrupt();

    }

    static class ThreadDemo implements Runnable {

        public Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 2; i++) {
                rtl.lock();
                System.out.println("获得锁的线程："+id);
                rtl.unlock();
            }
        }
    }


    static class DeadLockDemo implements Runnable {

        private Lock l1;

        private Lock l2;

        public DeadLockDemo(Lock l1, Lock l2) {
            this.l1 = l1;
            this.l2 = l2;
        }


        @Override
        public void run() {
            try {
                l1.lock();
                TimeUnit.MILLISECONDS.sleep(10);
                l2.lock();
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + "---》线程中断");
                e.printStackTrace();
            } finally {
                l1.unlock();
                l2.unlock();
            }
        }
    }


}

//阻塞队列
class MyBlockingQueue<E> {
    private int size;

    ReentrantLock lock = new ReentrantLock();

    LinkedList<E> list = new LinkedList<>();

    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();

    public MyBlockingQueue(int size) {
        this.size = size;
    }

    public void enqueue(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == size)//队列已满,在notFull条件上等待
                notFull.await();
            list.add(e);//入队:加入链表末尾
            System.out.println("入队：" +e);
            notEmpty.signal(); //通知在notEmpty条件上等待的线程
        } finally {
            lock.unlock();
        }
    }

    public E dequeue() throws InterruptedException {
        E e;
        lock.lock();
        try {
            while (list.size() == 0)//队列为空,在notEmpty条件上等待
                notEmpty.await();
            e = list.removeFirst();//出队:移除链表首元素
            System.out.println("出队："+e);
            notFull.signal();//通知在notFull条件上等待的线程
            return e;
        } finally {
            lock.unlock();
        }
    }
}



