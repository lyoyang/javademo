package com.lyoyang.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: Brian
 * @Date: 2020/6/4 17:17
 * @Description:
 */
public class ZkDemo {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("192.168.205.10", 5000, new CustomWatcher(latch));
        latch.await();
        System.out.println("zookeeper session establishd.");
        //同步创建
//        createSync(zk);

        //异步创建
        createAsync(zk);

        TimeUnit.MINUTES.sleep(2);

    }



    private static void createSync(ZooKeeper zk) throws KeeperException, InterruptedException {
        //创建节点 同步 临时节点
        zk.create("/tmp", "Hello,ZK".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //同步方式，临时顺序节点
        zk.create("/tmp", "Hello,ZK".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }


    private static void createAsync(ZooKeeper zk) {
        zk.create("/async", "async".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new IStringCallBack(), "I am context");
    }



    static class IStringCallBack implements AsyncCallback.StringCallback {

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            //rc 响应码  0-成功 -4-连接已断开 -110-指定节点已存在 -112-会话已过期
            System.out.println(String.format("create path success,rc:%s,path:%s,ctx:%s,name:%s", rc, path, ctx, name));
        }
    }










}
