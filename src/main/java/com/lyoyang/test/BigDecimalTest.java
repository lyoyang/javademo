package com.lyoyang.test;

import com.lyoyang.utils.BigDecimalUtils;
import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {


    @Test
    public void test() {
        String s = BigDecimalUtils.yuanToFenBigDecimal(new BigDecimal("0.10")).setScale(0).stripTrailingZeros().toPlainString();
        System.out.println(s);
    }

}
