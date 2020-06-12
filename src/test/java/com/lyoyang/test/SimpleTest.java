package com.lyoyang.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author: Brian
 * @Date: 2020/6/12 10:00
 * @Description:
 */
public class SimpleTest {

    @Test
    public void calc() {
        BigDecimal bigDecimal = new BigDecimal("456565.6586");
        System.out.println(bigDecimal.divide(BigDecimal.valueOf(100L)).setScale(6));
    }

}
