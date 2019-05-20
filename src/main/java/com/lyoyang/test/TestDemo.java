package com.lyoyang.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.lyoyang.entity.CalculationDto;
import com.lyoyang.entity.User;
import com.lyoyang.utils.CSVUtils;
import com.lyoyang.utils.DateUtil;
import org.junit.Test;

import java.io.File;
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
//        Object o = "13433";
//        System.out.println(1 << 4);
//        String str = "123";
//        int hashCode = str.hashCode();
//        System.out.println(hashCode);
//        BigDecimal res = new BigDecimal("75").multiply(new BigDecimal("0.7")).setScale(0, BigDecimal.ROUND_HALF_UP);
//        System.out.println(res);

//        BigDecimal ee = (new BigDecimal("500000").multiply(new BigDecimal("0.001")).subtract(new BigDecimal("35")))
//                .multiply(new BigDecimal("0.7")).setScale(0, BigDecimal.ROUND_HALF_UP);
//        System.out.println(ee);

    }

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
//        System.out.println(DateUtil.getStringFromDate(chAccDateStart, DateUtil.FORMAT_DATETIME));

        if(transDate.compareTo(chAccDateStart) >= 0) {
            calendar.add(Calendar.DATE, 1);
            System.out.println(DateUtil.getStringFromDate(calendar.getTime(), DateUtil.FORMAT_DATE));;
        } else {
            System.out.println(DateUtil.getStringFromDate(transDate, DateUtil.FORMAT_DATE));
        }
    }



    @Test
    public void testAbs() {
//        System.out.println(Math.abs(-1));
//        BigDecimal bigDecimal = new BigDecimal("0.003");
//        BigDecimal test = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
//        System.out.println(test);
//        Integer l1 = new Integer(2);
//        Integer l2= new Integer(5);
//        System.out.println(l1 > l2);
//        System.out.println(new BigDecimal("100").compareTo(new BigDecimal(1200)));
        BigDecimal tmp = (new BigDecimal("100").subtract(new BigDecimal("20"))).multiply(new BigDecimal("0.7")).setScale(0, BigDecimal.ROUND_HALF_UP);
        BigDecimal m = new BigDecimal("2000").multiply((new BigDecimal("0.002").subtract(new BigDecimal("0.001"))));
        System.out.println(tmp.add(m));
    }



    @Test
    public void tetsHHH() {
        System.out.println(fenToYuanBigDecimal(new BigDecimal("74956")));
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
    public void testExportCsv() {
//        File file = new File("D:/test.csv");
//        List<String> list = new ArrayList<>();
//        list.add("jim");
//        list.add("bob");
//        list.add("nan");
//        CSVUtils.exportCsv(file, list);
//        List<String> ll = Lists.newArrayList("12232");
//        System.out.println(ll);
        System.out.println(Integer.valueOf("123"));
//        new Integer(1).compareTo(Integer.getInteger("12323"));

        User user = new User();
        System.out.println(user.getCity().toString());
    }










}
