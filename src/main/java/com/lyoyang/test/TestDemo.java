package com.lyoyang.test;

import com.lyoyang.annotationdemo.AnnotationFactory;
import com.lyoyang.entity.User;
import com.lyoyang.utils.DateUtil;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;

public class TestDemo {

    private static String accTimeEnd = "2:30:00";
    private static String accTimeStart = "00:00:00" ;


    public static String getAccDate(Date transDate) {
        Date nowTime = new Date();
        String temp = DateUtil.getCertainDate(-1).trim();

        //获取D-1日清算日的截止时间 范围  00:00--2:30
        String dateStr = DateUtil.getStringFromDate(nowTime, DateUtil.FORMAT_DATE);
        Date accDateStart = DateUtil.getDateFromString(dateStr + " " + accTimeStart, DateUtil.FORMAT_DATETIME);
        Date accDateEnd = DateUtil.getDateFromString(dateStr + " " + accTimeEnd, DateUtil.FORMAT_DATETIME);
        if ((nowTime.compareTo(accDateStart) > 0) && (nowTime.compareTo(accDateEnd) < 0)) {
            if (temp.equals(DateUtil.getStringFromDate(transDate, DateUtil.FORMAT_DATE))) {
                return temp;
            } else {
                return DateUtil.getStringFromDate(nowTime, DateUtil.FORMAT_DATE);
            }
        } else {
            return DateUtil.getStringFromDate(nowTime, DateUtil.FORMAT_DATE);
        }
    }


    @Test
    public void test2() {
//        BigDecimal transAmount = new BigDecimal(80);
//        BigDecimal feeValue = transAmount.multiply(new BigDecimal(0.007)).setScale(0, BigDecimal.ROUND_HALF_UP);
//        System.out.println(feeValue);
        Object o = "13433";
        System.out.println(o.toString());
    }


}
