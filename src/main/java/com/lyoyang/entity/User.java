package com.lyoyang.entity;


import lombok.*;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
public class User {
    private int id;

    private Integer age;

    private String name;
    private String city;

    private BigDecimal work;

    private Optional<Student> student;



    public User() {
        this.work = BigDecimal.ZERO;
    }

    public User(Integer age, int id) {
        this.age = age;
    }

    public User(int id) {
        this.id = id;
    }
}
