package com.lyoyang.java8;

/**
 * @Auther: yangbing
 * @Date: 2019/3/27 9:53
 * @Description:
 */
@FunctionalInterface
public interface Convert<F,T> {
    T convert(F from);
}
