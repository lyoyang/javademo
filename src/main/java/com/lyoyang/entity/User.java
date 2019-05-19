package com.lyoyang.entity;


import com.lyoyang.annotationdemo.InitValue;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class User {
    private int id;

    private Integer age;

    @InitValue(value = "bob")
    private String name;
    private String city;

    private BigDecimal work;



    public User() {
        this.work = BigDecimal.ZERO;
    }

    public User(int id) {
        this.id = id;
    }
}
