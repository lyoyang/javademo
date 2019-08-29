package com.lyoyang.test;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: yangbing
 * @Date: 2019/8/9 16:27
 * @Description: 正则表达式
 */
public class RexDemo {

    @Test
    public void test1() {
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher("123");
//        System.out.println(matcher.matches());
        while (matcher.find()) {
            System.out.println("start=" + matcher.start());
            System.out.println("end=" + matcher.end());
        }
    }


    @Test
    public void test2() {
//        String regx = "[1-9][0-9]{4,9}";
//        System.out.println("12345".matches(regx));
        String s = "张三@@@李四￥￥王五ssssssss江流儿";
        String regex = "(.)\\1+";
        String[] split = s.split(regex);
        for (String tmp : split) {
            System.out.println(tmp);
        }
    }


}
