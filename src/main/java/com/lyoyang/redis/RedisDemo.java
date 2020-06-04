package com.lyoyang.redis;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: Brian
 * @Date: 2020/6/4 17:16
 * @Description:
 */
public class RedisDemo {

    private ExecutorService EXECUTOR = Executors.newFixedThreadPool(5);



    @Test
    public void testString() {
        String res = getJedis().set("subject", "PE");
        System.out.println(res);
    }

    @Test
    public void testHash() {
        getJedis().hset("person", "id", "1");
        getJedis().hset("person", "name", "jim");
        getJedis().hset("person", "age", "12");
    }


    @Test
    public void testSet() {
        getJedis().sadd("weather", "hot", "cold", "cool");
    }


    @Test
    public void distributionLock() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            EXECUTOR.execute(() -> {
                try {
                    boolean lock = lock();
                    while (!lock) {
                        System.out.println(Thread.currentThread() + "not get lock");
                        TimeUnit.SECONDS.sleep(10);
                        lock = lock();
                    }
                    System.out.println(Thread.currentThread() + "get lock");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    unlock();
                }
            });
        }
        Thread.currentThread().join();

    }




    private boolean lock() {
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(30);
        String res = getJedis().set("lock:user", "user", setParams);
        return StringUtils.isEmpty(res) ? false : true;
    }


    private void unlock() {
        getJedis().del("lock:user");
    }


    private Jedis getJedis() {
        return new Jedis("127.0.0.1", 6379);
    }


}
