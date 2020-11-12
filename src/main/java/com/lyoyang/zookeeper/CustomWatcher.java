package com.lyoyang.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Brian
 * @Date: 2020/7/2 17:06
 * @Description:
 */
public class CustomWatcher implements Watcher {

    private CountDownLatch countDownLatch;

    public CustomWatcher(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("receive watched event:" + event);
        if (Event.KeeperState.SyncConnected == event.getState()) {
            countDownLatch.countDown();
        }
    }
}
