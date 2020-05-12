package com.lyoyang.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.lyoyang.entity.AdditionalRequestDto;
import com.lyoyang.entity.Student;
import com.lyoyang.entity.User;
import com.lyoyang.utils.AESUtil;
import com.lyoyang.utils.DateUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestDemo {

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
    public void testClone() {
        User jim = User.builder().name("jim").build();
        User bob = User.builder().name("bob").build();
        List<User> list = new ArrayList<>();
//        list.add(jim);
//        list.add(bob);
        List<String> collect = list.stream().map(User::getName).collect(Collectors.toList());
        System.out.println(collect.toString());

    }

    @Test
    public void testInvoke() throws ClassNotFoundException {
        long instantNow = Instant.now().toEpochMilli();
        System.out.println(instantNow);


    }



    public void testSync(String mchId) throws InterruptedException {
        synchronized (mchId) {
            System.out.println("this is " + mchId);
            TimeUnit.SECONDS.sleep(10);
            System.out.println("completed " + mchId);
        }
    }


    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        IntStream.range(0, 2).forEach(a -> {
            String mchId = "12345";
            new Thread(() -> {
                try {
                    testDemo.testSync(mchId + a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }





}
