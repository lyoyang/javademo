package com.lyoyang.guava.cache;

import lombok.Data;

@Data
public class Student {

    private String id;
    private String name;
    private String classNo;


    public Student() {
    }

    public Student(String id, String name, String classNo) {
        this.id = id;
        this.name = name;
        this.classNo = classNo;
    }
}
