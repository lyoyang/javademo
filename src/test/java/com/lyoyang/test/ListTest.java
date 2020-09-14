package com.lyoyang.test;

import com.lyoyang.entity.Member;
import com.lyoyang.utils.JDBCUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

    @Test
    public void test1() {
//        List<String> list = Arrays.asList("1", "2", "3");
//        Arrays.copyOf(list.toArray(), list.size() + 1);
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


    @Test
    public void testLinkedList() {
//        LinkedList<Integer> link = new LinkedList<>();
//        link.pop();
//        link.removeLast();
        String insertSql = "INSERT INTO `member` (mobile, phone) VALUES ('%s', '%s')";
        String format = String.format(insertSql, "1234", "3456");
        System.out.println(format);
        String querySql = "SELECT *  FROM member where mobile = '%s'";
        List<Member> query = JDBCUtils.query(String.format(querySql, "15735104203"), Member.class);
        System.out.println(query);
    }



}
