package com.lyoyang.concurrent.designpattern.observable;
@FunctionalInterface
public interface Task<T> {
    T call();
}
