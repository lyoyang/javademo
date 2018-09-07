package com.lyoyang.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lyoyang.entity.Student;
import com.lyoyang.utils.ValidationUtils;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class TestDemo {

    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList();
        list.add("jim");
        list.add("bob");
        list.add("1234");
//        System.out.println(list.toString());
//        Collections.sort(list);
//        System.out.println(list.toString());
//        User user = new User();
//        test_demo2()
    }


    public static void test_demo2() {
        Student student = new Student();
        student.setId(12);
        student.setUsername("jimgssgfdg");
        String validate = ValidationUtils.validate(student);
        System.out.println(validate);
        System.out.println(student);
    }


    @Test
    public static void test_bigdecimal() {
        BigDecimal bigDecimal = new BigDecimal(23.4);
        BigDecimal add = bigDecimal.add(new BigDecimal(4));
//        System.out.println(add);
        System.out.println(Config.NAME);
    }

    @Test
    public void test_hashMap() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("1", "jim");
        map.put("2", "bob");
    }

}
