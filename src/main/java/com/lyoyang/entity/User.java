package com.lyoyang.entity;

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
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
