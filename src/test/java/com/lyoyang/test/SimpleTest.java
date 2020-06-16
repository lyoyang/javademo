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
        BigDecimal bigDecimal = new BigDecimal("456565.65867876979");
        System.out.println(bigDecimal.multiply(BigDecimal.valueOf(100L)).setScale(4, BigDecimal.ROUND_HALF_UP));
    }

}
