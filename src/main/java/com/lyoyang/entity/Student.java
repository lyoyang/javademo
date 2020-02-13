package com.lyoyang.entity;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

public class Student {

    @NotBlank(message = "id不能为空")
    @Range(min = 6, max = 10, message = "id范围6-10")
    private Integer id;

    @NotBlank(message = "名字不能为空")
    @Range(min = 5, max = 10, message = "名字范围5-10")
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
