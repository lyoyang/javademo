package com.lyoyang.annotation;

import lombok.Data;

@Data
public class Banana {

    @FruitProvider(id = 12, name = "banana", address = "beijing")
    private String fruitProvider;
}
