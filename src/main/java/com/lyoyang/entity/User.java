package com.lyoyang.entity;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

@Data
@AllArgsConstructor
@Builder
@ToString
public class User implements Serializable {
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
