package com.lyoyang.concurrent.designmode.observable;

/**
 * @author: yangbing
 * @Date: 2020/2/24 11:52
 * @Description:
 */
@FunctionalInterface
public interface Task<T> {
    T call();
}
