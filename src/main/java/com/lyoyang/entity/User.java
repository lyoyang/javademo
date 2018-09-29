package com.lyoyang.entity;


import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int id;

    private Integer age;

    private String name;
    private String city;

    private BigDecimal work;

    public User(int id) {
        this.id = id;
    }
}
