package com.lyoyang.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

    @Test
    public void test1() {
        List<String> list = Arrays.asList("1", "2", "3");
        Arrays.copyOf(list.toArray(), list.size() + 1);
    }

    @Test
    public void test2() {
        CopyOnWriteArrayList<String> writeArrayList = new CopyOnWriteArrayList<>();
        writeArrayList.add("1");
        writeArrayList.add("2");
        writeArrayList.add("3");
        writeArrayList.add("4");
        Iterator<String> iterator = writeArrayList.iterator();
        System.out.println(writeArrayList);
        writeArrayList.add(2, "99");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }



}
