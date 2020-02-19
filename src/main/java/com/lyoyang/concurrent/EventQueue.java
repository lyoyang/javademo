package com.lyoyang.concurrent;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class EventQueue {

    static class Event {}


    private final int max;


    private final static int DEFAULT_MAX = 10;

    private final LinkedList<Event> linkedList = new LinkedList<>();

    public EventQueue() {
        this(DEFAULT_MAX);
    }

    public EventQueue(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (linkedList) {
            if (linkedList.size() >= max) {
                try {
                    console("the queue is full");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
            linkedList.addLast(event);
            linkedList.notify();
            console("the new event is submitted.");
        }




    }

    public void take() {
        synchronized (linkedList) {
            if (linkedList.size() <= 0) {
                try {
                    console("the queue is empty");
                    linkedList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = linkedList.removeFirst();
            linkedList.notify();
            console("the event " + event +"is submitted.");
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
        }, "Producer").start();



        new Thread(() -> {
            for (;;) {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                eventQueue.take();
            }
        }, "Consumer").start();
    }




}
