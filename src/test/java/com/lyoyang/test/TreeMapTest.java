package com.lyoyang.test;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapTest {

    @Test
    public void test1() {
        TreeMap<String, Object> treeMap = new TreeMap<>();

        treeMap.put("3", "alie");
        treeMap.put("1", "jim");
        treeMap.put("2", "bob");
        treeMap.put("4", "brian");
        treeMap.put("5", "lili");
        System.out.println(treeMap.firstEntry().getKey());
        SortedMap<String, Object> tailMap = treeMap.tailMap("3");
        System.out.println(tailMap);
        System.out.println(tailMap.firstKey());
        SortedMap<String, Object> headMap = treeMap.headMap("3");
        System.out.println(headMap);

    }

}
