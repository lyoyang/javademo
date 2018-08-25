package com.lyoyang.concurrent.lock;

/**
 * CAS操作
 * 它包含三个参数CAS(V,E,N):
 * V表示要更新的变量，E表示预期值，N表示新值。仅当V值等于E值时，才会将V的值设为N
 * 如果V值和E值不同，则说明已经有其他线程做了更新，则当前线程什么都不做
 * 最后，CAS返回当前V的真实值
 */
public class LockDemo4 {
}
