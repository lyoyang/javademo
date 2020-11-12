package com.lyoyang.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * @author: Brian
 * @Date: 2020/7/3 10:41
 * @Description:
 */
public class CuratorClient {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        CuratorFramework newClient = CuratorFrameworkFactory.newClient("192.168.205.10:2181", 5000, 3000, retryPolicy);
//        newClient.start();

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.205.10")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
//                .namespace("base")
                .build();
        client.start();
//        TimeUnit.SECONDS.sleep(20);
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/tmp", "tmpData".getBytes());
    }

}
