package com.lyoyang.java8;

import com.lyoyang.entity.Student;
import com.lyoyang.entity.User;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

public class OptionalDemo {

    @Test
    public void testOptional() {
        Optional.ofNullable(getUserName(null)).ifPresent(System.out::println);
    }

    public static String getUserName(User user) {
        return Optional.ofNullable(user).flatMap(User::getStudent).map(Student::getUsername).orElse("unknown");
    }






}
