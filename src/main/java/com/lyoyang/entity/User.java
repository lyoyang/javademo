package com.lyoyang.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private Integer age;
    private String name;
    private String city;

    public User(int id) {
        this.id = id;
    }
}
