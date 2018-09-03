package com.lyoyang.test;

import com.google.common.collect.Lists;
import com.lyoyang.entity.Student;
import com.lyoyang.utils.ValidationUtils;

import java.util.ArrayList;

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
        test_demo2();
    }

    public static void test_demo2() {
        Student student = new Student();
        student.setId(12);
        student.setUsername("jimgssgfdg");
        String validate = ValidationUtils.validate(student);
        System.out.println(validate);
        System.out.println(student);
    }

}
