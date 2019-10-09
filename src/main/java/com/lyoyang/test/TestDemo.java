package com.lyoyang.test;

import com.google.common.io.Files;
import com.lyoyang.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;

public class TestDemo {

    private static String accTimeEnd = "2:30:00";
    private static String accTimeStart = "00:00:00" ;



    @Test
    public void testCast() {
        Date transDate = DateUtil.getDateFromString("2019-05-13 23:59:59", DateUtil.FORMAT_DATETIME);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(transDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        //范围开始23:00
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        calendar.add(Calendar.DATE, 1);
        Date chAccDateStart = DateUtil.getDateTimeFromDate(calendar.getTime(), DateUtil.FORMAT_DATETIME);
        if(transDate.compareTo(chAccDateStart) >= 0) {
            calendar.add(Calendar.DATE, 1);
            System.out.println(DateUtil.getStringFromDate(calendar.getTime(), DateUtil.FORMAT_DATE));;
        } else {
            System.out.println(DateUtil.getStringFromDate(transDate, DateUtil.FORMAT_DATE));
        }
    }



    public static BigDecimal fenToYuanBigDecimal(BigDecimal nums) {
        if (nums == null || nums.equals(BigDecimal.ZERO)) {
            return BigDecimal.ZERO;
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(100));
        return result.setScale(2);
    }


    public static String liToYuanStr(BigDecimal nums) {
        if (nums == null) {
            return "0";
        }
        BigDecimal result = nums.divide(BigDecimal.valueOf(1000L));
        if (result.equals(BigDecimal.ZERO)) {
            return "0";
        }
        return result.setScale(3).toPlainString();
    }

    @Test
    public void testMessageFormat() {
        System.out.println(BigDecimal.valueOf(400L).negate().toPlainString());
        String msg = "oh,{0} is ''a'' pig";
        String format = MessageFormat.format(msg, "lisi");
        System.out.println(format);
        System.out.println(MessageFormat.format("{0}", 12345678.9));
        System.out.println(MessageFormat.format("oh, {0, number, #.##} is a good number", Double.valueOf("3.23445")));
    }

    @Test
    public void CommonTest() throws IOException, InterruptedException {
        Date dateFromString = DateUtil.getDateFromString("2019-10-01", DateUtil.FORMAT_DATE);
        for (int i = 1; i <= 30; i++) {
            Date certainDate = DateUtil.getCertainDate(dateFromString, -i);
            String stringFromDate = DateUtil.getStringFromDate(certainDate, DateUtil.FORMAT_DATE);
            String beginDate = stringFromDate + " 00:00:00";
            String endDate = stringFromDate + " 23:59:59";
            System.out.println(beginDate + "------" + endDate);
        }

    }


    @Test
    public void CommonTest2() throws IOException, InterruptedException {
        Date dateFromString = DateUtil.getDateFromString("2019-09-24", DateUtil.FORMAT_DATE);
        for (int i = 1; i <= 23; i++) {
            Date certainDate = DateUtil.getCertainDate(dateFromString, -i);
            System.out.println(DateUtil.getStringFromDate(certainDate, DateUtil.FORMAT_DATE));
        }

    }

    @Test
    public void CommonTest3() throws IOException, InterruptedException {
       while (true) {
           System.out.println("hello");
           Thread.sleep(3000L);
       }

    }

}
