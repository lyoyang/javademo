package com.lyoyang.concurrent.designpattern.observable;

public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifeCycle<T> taskLifeCycle;

    private final Task<T> task;

    private Cycle cycle;

    public ObservableThread(Task<T> task) {
        this(new TaskLifeCycle.EmptyLifyCycle(), task);
    }

    public ObservableThread(TaskLifeCycle<T> taskLifeCycle, Task<T> task) {
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
            T result = task.call();
            this.update(Cycle.DONE, result, null);
        } catch (Exception e) {
            this.update(Cycle.ERROE, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (taskLifeCycle == null) {
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
                case ERROE:
                    this.taskLifeCycle.onError(currentThread(), e);
                    break;
            }
        } catch (Exception ex) {
            if (cycle == Cycle.ERROE) {
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
