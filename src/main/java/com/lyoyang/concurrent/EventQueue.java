package com.lyoyang.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;

public class EventQueue {

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    static class Event {}

    private final int max;

    private final static int DEFAULT_MAX = 10;

    private final LinkedList<Integer> linkedList = new LinkedList<>();

    public EventQueue() {
        this(DEFAULT_MAX);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (linkedList) {
            while (linkedList.size() >= max) {
                try {
                    console("the queue is full");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int andIncrement = atomicInteger.getAndIncrement();
            linkedList.addLast(andIncrement);
            linkedList.notifyAll();
            console("the new event [" + andIncrement+ "] offered，size=" + linkedList.size());
        }
    }

    public void take() {
        synchronized (linkedList) {
            while (linkedList.size() <= 0) {
                try {
                    console("the queue is empty");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Integer data = linkedList.removeFirst();
            linkedList.notifyAll();
            console("the event [" + data + "] is taked,size=" + linkedList.size());
        }
    }


    private void console(String msg) {
        System.out.printf("%s:%s\n", currentThread().getName(), msg);
    }


    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();

        new Thread(() -> {
            for (;;) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer1").start();
        new Thread(() -> {
            for (;;) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer2").start();


        new Thread(() -> {
            for (;;) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eventQueue.take();
            }
        }, "Consumer1").start();

        new Thread(() -> {
            for (;;) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eventQueue.take();
            }
        }, "Consumer2").start();
        new Thread(() -> {
            for (;;) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eventQueue.take();
            }
        }, "Consumer3").start();
    }




}
