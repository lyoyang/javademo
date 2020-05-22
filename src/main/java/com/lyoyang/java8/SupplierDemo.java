package com.lyoyang.java8;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author: Brian
 * @Date: 2020/5/22 16:23
 * @Description:
 */
public class SupplierDemo {


    public static void main(String[] args) {
        Optional.of(getBySupplier(() -> "hello")).ifPresent(System.out::println);
    }


    public static String getBySupplier(Supplier<String> supplier) {
        return supplier.get();
    }


}
