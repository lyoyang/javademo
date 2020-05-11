package com.lyoyang.concurrent.designmode.observable;

import ch.qos.logback.core.spi.LifeCycle;

/**
 * @author: yangbing
 * @Date: 2020/2/24 11:54
 * @Description:
 */
public class ObsevableThread<T> extends Thread implements Observable {


    private final TaskLifeCycle<T> taskLifeCycle;

    private final Task<T> task;

    private Cycle cycle;

    public ObsevableThread(Task<T> task) {
        this(new TaskLifeCycle.DefaultTaskLifeCycle<>(), task);
    }

    public ObsevableThread(TaskLifeCycle<T> taskLifeCycle, Task<T> task) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("the task is required");
        }
        this.taskLifeCycle = taskLifeCycle;
        this.task = task;
    }


    @Override
    public final void run() {
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
     }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (this.taskLifeCycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.taskLifeCycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.taskLifeCycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.taskLifeCycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.taskLifeCycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }

}
