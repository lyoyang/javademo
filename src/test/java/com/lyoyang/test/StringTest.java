package com.lyoyang.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringTest {

    @Test
    public void testStr() {
        String str1 = "abc";
        String str2 = new String("abc");
        String str3 = str2.intern();
        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str1 == str3);
    }


    @Test
    public void testIntern() throws UnsupportedEncodingException {
        String a = new String("abc").intern();
        String b = new String("abc").intern();
        System.out.println(a == b);
        String c = "abc";
        String d = new String("abc");
        System.out.println(c == d);
        System.out.println(URLEncoder.encode("中国工商银行(杭州余杭支行)", "utf-8"));
    }


    @Test
    public void subStrTest() throws UnsupportedEncodingException {
        String str = "http://api.map.baidu.com/marker?location=30.418884,120.314628&title=中国工商银行(杭州余杭支行)&output=html";
        String title = StringUtils.substringBetween(str, "title=", "&");
        System.out.println(str.replace(title, URLEncoder.encode(title, "utf-8")));
    }



}
