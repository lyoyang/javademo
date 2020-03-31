package com.lyoyang.concurrent.designpattern.observable;

public interface Observable {

    enum Cycle {
        STARTED,RUNNING,DONE,ERROE
    }

    Cycle getCycle();

    void start();

    void interrupt();

}
