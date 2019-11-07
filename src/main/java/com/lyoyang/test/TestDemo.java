package com.lyoyang.test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.lyoyang.utils.DateUtil;
import javafx.concurrent.Task;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        Date dateFromString = DateUtil.getDateFromString("2019-08-27", DateUtil.FORMAT_DATE);
        for (int i = 1; i <= 10; i++) {
            Date certainDate = DateUtil.getCertainDate(dateFromString, -i);
            System.out.println(DateUtil.getStringFromDate(certainDate, DateUtil.FORMAT_DATE));
        }

    }

    @Test
    public void CommonTest3() throws IOException, InterruptedException {
        Map<String, Object> map = new HashMap<>();
//        System.out.println(Math.floorDiv(-9, 4));
//        System.out.println(11 >> 2);
//        System.out.println(Math.floorMod(7, 4));
//        System.out.println(Math.floorMod(-7, 4));
//        System.out.println(Math.floorMod(7, -4));
//        System.out.println(Math.floorMod(-7, -4));
//        System.out.println(9 ^ -7);

//        System.out.println("双免：" + new BigDecimal("32800").multiply(new BigDecimal("0.003")).setScale(0, BigDecimal.ROUND_UP));
//        System.out.println("非双免：" + new BigDecimal("").multiply(new BigDecimal("")).setScale(0, BigDecimal.ROUND_UP));
//        System.out.println("70".equals(null));
//        System.out.println("1".equals(new Byte("1").toString()));
//        File file = new File("E://测试文档/merchant.sql");
//        file.delete();
//        System.out.println(Math.random());
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        List<String> l1 = Arrays.asList("1", "2", "3");
        List<String> l2 = Arrays.asList("1", "3", "4", "5");
        Set<String> set = new HashSet<>();
        set.addAll(l1);
        set.addAll(l2);
        Collections.sort();
        System.out.println(set);
    }

}
