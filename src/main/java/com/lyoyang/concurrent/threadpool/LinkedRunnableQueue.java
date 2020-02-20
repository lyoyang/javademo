package com.lyoyang.concurrent.threadpool;

import java.util.LinkedList;

public class LinkedRunnableQueue implements RunnableQueue {

    private final int limit;

    private final DenyPolicy denyPolicy;

    private final LinkedList<Runnable> runnables = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnables) {
            if (runnables.size() >= limit ) {
                denyPolicy.rejeict(runnable, threadPool);
            } else {
                runnables.add(runnable);
                runnables.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnables) {
            if (runnables.isEmpty()) {
                try {
                    runnables.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            return runnables.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnables) {
            return runnables.size();
        }
    }
}
