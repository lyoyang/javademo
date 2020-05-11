package com.lyoyang.concurrent.designmode.observable;

/**
 * @author: yangbing
 * @Date: 2020/2/24 11:23
 * @Description:
 */
public interface Observable {
    enum Cycle {
        STARTED,RUNNING,DONE,ERROR
    }
    //获取当前任务的生命周期状态
    Cycle getCycle();
    //启动线程
    void start();
    //打断线程
    void interrupt();
}
