package com.lyoyang.concurrent.designpattern.observable;

public interface TaskLifeCycle<T> {

    void onStart(Thread t);

    void onRunning(Thread t);

    void onFinish(Thread t, T result);

    void onError(Thread t, Exception e);

    class EmptyLifyCycle<T> implements TaskLifeCycle<T> {


        @Override
        public void onStart(Thread t) {
        }

        @Override
        public void onRunning(Thread t) {

        }

        @Override
        public void onFinish(Thread t, T result) {

        }

        @Override
        public void onError(Thread t, Exception e) {

        }
    }
}
