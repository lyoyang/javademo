package com.lyoyang.test;

import org.junit.Test;

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
    public void testIntern() {
        String a = new String("abc").intern();
        String b = new String("abc").intern();
        System.out.println(a == b);
    }


}
