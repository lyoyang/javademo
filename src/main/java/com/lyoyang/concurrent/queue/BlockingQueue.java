package com.lyoyang.concurrent.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: Brian
 * @Date: 2020/6/10 18:47
 * @Description:
 */
public class BlockingQueue {


    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<Integer>(3);
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
        //pool队列为空时，返回null
        System.out.println(blockingQueue.poll());
        //take队列为空时，会阻塞
        System.out.println(blockingQueue.take());
        //peek队列为空时，返回null
        System.out.println(blockingQueue.peek());

    }

}
