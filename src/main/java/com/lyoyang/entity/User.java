package com.lyoyang.entity;


import lombok.Data;

@Data
public class User {
    private int id;
    private Integer age;
    private String name;
    private String city;
    public User(){}
    public User(int id, String name, String city){
        this.id = id;
        this.name = name;
        this.city = city;
    }
    public User(Integer age){
        this.age = age;
    }
}
