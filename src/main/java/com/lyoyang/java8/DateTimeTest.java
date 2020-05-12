package com.lyoyang.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeTest {

    public static void main(String[] args) {
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//        System.out.println(LocalDate.parse("2020-03-12").toString());
//        System.out.println(LocalDate.parse("2020-03-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
        System.out.println(LocalDateTime.now().getHour());


        int hour = LocalDateTime.parse("2020-04-17 22:12:34", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).getHour();
        System.out.println(hour);

    }

}
