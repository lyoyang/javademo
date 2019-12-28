package com.lyoyang.guava.concurrent;

import com.google.common.util.concurrent.Monitor;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.*;

public class MonitorExample {


    public static void main(String[] args) {
        final MonitorGuard monitorGuard = new MonitorGuard();
        final AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (;;) {
                    int data = atomicInteger.getAndIncrement();
                    System.out.println(currentThread() + "|offer:" + data);
                    monitorGuard.offer(data);
                    try {
                        TimeUnit.MILLISECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for(;;) {
                    int value = monitorGuard.take();
                    System.out.println(currentThread() + "|take:" + value);
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }





    static class MonitorGuard {
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10;
        private final Monitor monitor = new Monitor();
        private final Monitor.Guard CAN_OFFER = monitor.newGuard(() -> queue.size() < MAX);
        private final Monitor.Guard CAN_TAKE = monitor.newGuard(() -> !queue.isEmpty());


        public void offer(int value) {
            try {
                monitor.enterWhen(CAN_OFFER);
                queue.addLast(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.leave();
            }
        }

        public int take() {
            Integer value = null;
            try {
                monitor.enterWhen(CAN_TAKE);
                value = queue.removeFirst();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.leave();
            }
            return value;
        }
    }



    static class LockCondition {
        private final ReentrantLock rtl = new ReentrantLock();
        private final Condition FULL_CONDITION = rtl.newCondition();
        private final Condition EMPTY_CONDITION = rtl.newCondition();
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10;

        public void offer(int value) {
            try {
                rtl.lock();
                while (queue.size() >= MAX) {
                    FULL_CONDITION.await();
                }
                queue.addLast(value);
                EMPTY_CONDITION.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rtl.unlock();
            }
        }

        public int take() {
            Integer value = null;
            try {
                rtl.lock();
                while (queue.isEmpty()) {
                    EMPTY_CONDITION.await();
                }
                value = queue.removeFirst();
                FULL_CONDITION.signalAll();
                return value;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rtl.unlock();
            }
            return value;
        }

    }



    static class Synchronized {
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10;

        public void offer(int value) {
            synchronized (queue) {
                while (queue.size() >= MAX) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(value);
                queue.notifyAll();
            }
        }


        public int take() {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Integer value = queue.removeFirst();
                queue.notifyAll();
                return value;
            }
        }

    }
}
