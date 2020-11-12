package com.lyoyang.zookeeper;


import org.I0Itec.zkclient.ZkClient;

/**
 * @author: Brian
 * @Date: 2020/7/2 17:59
 * @Description:
 */
public class ZkClientDemo {

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("192.168.205.10:2181", 6000);
        System.out.println("zkClient established");
    }

}
