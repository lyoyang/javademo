package com.lyoyang.test;

import com.alibaba.fastjson.JSONObject;
import com.lyoyang.guava.cache.Student;
import org.fluentd.logger.FluentLogger;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
//        Iterator<String> iterator = writeArrayList.iterator();
//        System.out.println(writeArrayList);
//        writeArrayList.add(2, "99");
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
    }


    @Test
    public void testLinkedList() {
        ArrayList<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("2"));
        list.add(new BigDecimal("3"));
        BigDecimal reduce = list.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(reduce.toString());
    }

    @Test
    public void testLock() {
        int num = getNum();
        System.out.println(num);
    }


    public int getNum() {
        int i = 0;
        for (; i < 10; i++) {
//            if (i == 2) {
//                return i;
//            }
            if (i == 3) {
                try {
                    int d = i / 0;
                } catch (Exception e) {
                    break;
                }
            }
        }
        return i;
    }



    @Test
    public void testBreak() {
        System.out.println(new BigDecimal("0.01").toPlainString());
    }

    public static void main(String[] args) {
        FluentLogger LOG = FluentLogger.getLogger("fluentd.test", "10.10.220.121", 24224);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("from", "userA");
        data.put("to", "userB");
        Student student = new Student();
        student.setClassNo("1234");
        student.setId("12");
        student.setName("bruce");
        data.put("stu", student);
        LOG.log("java", data);
        LOG.log("java", data);
        LOG.log("java", data);
        LOG.log("java", data);

    }




}
