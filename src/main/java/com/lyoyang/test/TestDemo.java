package com.lyoyang.test;


import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.ArrayList;
import java.util.Random;

public class TestDemo {



    public static void main(String[] args) throws InterruptedException {
//        Random random = new Random();
//        BigDecimal fee = new BigDecimal("0.0067");
//        BigDecimal spFee = new BigDecimal("0.0035");
//        for (int i = 0 ; i < 100; i++) {
//            int data = random.nextInt(9999999);
//            BigDecimal amount = new BigDecimal(Integer.toString(data));
//            BigDecimal feeValue = amount.multiply(fee).setScale(0, BigDecimal.ROUND_HALF_UP);
//            BigDecimal spValue = amount.multiply(spFee).setScale(0, BigDecimal.ROUND_HALF_UP);
//            BigDecimal bigDecimal = feeValue.subtract(spValue);
//            BigDecimal distanceValue = amount.multiply(fee.subtract(spFee)).setScale(0, BigDecimal.ROUND_HALF_UP);
//            if (bigDecimal.compareTo(distanceValue) != 0) {
//                System.out.printf("amount=%s,no equal.distance=%s", amount, (bigDecimal.subtract(distanceValue)));
//                System.out.println();
//            }
//        }

//        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();
//        synchronousQueue.put(2);
//        System.out.println(synchronousQueue.take());
//        synchronousQueue.put(3);
//        synchronousQueue.put(4);
//        synchronousQueue.add(4);
//        synchronousQueue.offer(4);
//        synchronousQueue.offer(3);
//        System.out.println(synchronousQueue.take());
//        System.out.println(synchronousQueue.take());

//        new Thread(() -> {
//            try {
//                for (int i = 0; i < 10; i++) {
//                    TimeUnit.SECONDS.sleep(5);
//                    synchronousQueue.put(i);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        new Thread(() -> {
//            try {
//                while (true) {
//                    System.out.println(synchronousQueue.take());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();

        String cookie = "TWlYckMwa0ZpekV2ZFJ5dGtPUzFhdyUzRCUzRDpEYVhSMzZzUCUyQiUyQnJuJTJGNWVScVpvNXVBJTNEJTNE";
        String[] strings = decodeCookie(cookie);
        System.out.println(JSONObject.toJSONString(strings));


    }




    public static String[] decodeCookie(String cookieValue)  {
        for (int j = 0; j < cookieValue.length() % 4; j++) {
            cookieValue = cookieValue + "=";
        }
        try {
            Base64.getDecoder().decode(cookieValue.getBytes());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String cookieAsPlainText = new String(Base64.getDecoder().decode(cookieValue.getBytes()));

        String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText,
                ":");

        for (int i = 0; i < tokens.length; i++)
        {
            try
            {
                tokens[i] = URLDecoder.decode(tokens[i], StandardCharsets.UTF_8.toString());
            }
            catch (UnsupportedEncodingException e)
            {
               e.printStackTrace();
            }
        }
        return tokens;
    }


}
