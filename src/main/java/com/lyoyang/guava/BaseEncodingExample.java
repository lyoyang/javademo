package com.lyoyang.guava;

import com.google.common.io.BaseEncoding;
import org.junit.Test;

public class BaseEncodingExample {

    @Test
    public void base64Test() {
        BaseEncoding baseEncoding = BaseEncoding.base64();
        System.out.println(baseEncoding.encode("hello".getBytes()));
        System.out.println(baseEncoding.decode("35353533"));
    }


}
