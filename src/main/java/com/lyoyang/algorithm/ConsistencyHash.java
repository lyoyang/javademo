package com.lyoyang.algorithm;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性hash算法
 */
public class ConsistencyHash {
    //虚拟节点
    private TreeMap<Long, String> nodes;
    //真实机器节点
    private List<String> shards;
    //每个机器节点关联的虚拟节点个数
    private final int VIRTUAL_NODE_NUM = 100;


    public ConsistencyHash(List<String> shards) {
        super();
        this.shards = shards;
        init();
    }

    /**
     * 初始化一致性hash环
     */
    private void init() {
        nodes = new TreeMap<>();
        for (int i = 0; i < shards.size(); i++) {
            final String shardInfo = shards.get(i);
            for (int n = 0; n < VIRTUAL_NODE_NUM; n++) {
                nodes.put(hash("SHARD-" + i + "-NODE-" + n), shardInfo);
            }
        }
    }


    public String getShardInfo(String key) {
        //沿环的顺时针找到一个虚拟节点
        SortedMap<Long, String> tailMap = nodes.tailMap(hash(key));
        if (tailMap.size() == 0) {
            return nodes.get(nodes.firstKey());
        }
        //返回虚拟节点对应的真实节点的信息
        return tailMap.get(tailMap.firstKey());
    }

    /**
     *  MurMurHash算法，是非加密HASH算法，性能很高，
     *  比传统的CRC32,MD5，SHA-1（这两个算法都是加密HASH算法，复杂度本身就很高，带来的性能上的损害也不可避免）
     *  等HASH算法要快很多，而且据说这个算法的碰撞率很低.
     *  http://murmurhash.googlepages.com/
     */
    private Long hash(String key) {
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }


    private void printThreeNode() {
        if (nodes != null && !nodes.isEmpty()) {
            nodes.forEach((hashKsy, node) -> {
                System.out.println(node + "====>" + hashKsy);
            });
        }
    }


    public static void main(String[] args) {
        List<String> list = Arrays.asList("192.168.2.1:8080", "192.168.2.2:8080"
                , "192.168.2.3:8080", "192.168.2.4:8080");
        ConsistencyHash consistencyHash = new ConsistencyHash(list);
        consistencyHash.printThreeNode();
        System.out.println("==============");
        System.out.println(consistencyHash.getShardInfo(""));
    }





}
