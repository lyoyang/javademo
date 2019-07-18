package com.lyoyang.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lyoyang.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: yangbing
 * @Date: 2019/7/15 15:47
 * @Description:
 */
public class ThreadPoolDemo {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();
    private static final ExecutorService STATISTIC_THREAD_POOL = new ThreadPoolExecutor(5, 10, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(512), namedThreadFactory, new ThreadPoolExecutor.DiscardPolicy());


    @Test
    public void testRunner() {

        List<Callable<String>> callables = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("demo" + i);
            callables.add(new TestRunner(user));
        }

        try {
            STATISTIC_THREAD_POOL.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




    class TestRunner implements Callable<String> {
        private User user;

        public TestRunner(User user) {
            this.user = user;
        }

        @Override
        public String call() throws Exception {
            System.out.println(JSONObject.toJSONString(user));
            return "OK";
        }
    }

}
