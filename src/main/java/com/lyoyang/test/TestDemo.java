package com.lyoyang.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.lyoyang.entity.User;
import com.lyoyang.utils.AESUtil;
import com.lyoyang.utils.DateUtil;
import com.lyoyang.utils.RsaKeyUtil;
import com.sun.xml.internal.ws.util.StreamUtils;
import javafx.concurrent.Task;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import sun.misc.Unsafe;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

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
    public static void main(String[] args) throws IOException {
        int i = 100;
        for (; i<10; ++i) {
            System.out.println(1);
        }
        System.out.println(i);
    }

    private static final AtomicInteger atomicInteger = new AtomicInteger(1);

    private ExecutorService pool = Executors.newFixedThreadPool(10);


    public static final String PRIVATEKEYSTR = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIacOnzq4HcJcSVkIQqMVTTkS3MK\n" +
            "mpwVgk5VUWYCIQ3+ZPutDlo5TRDl07fy3vUC6tKfPI4jCECDH0fnHcbGRxvL2Q5rw0UPknUTVZgF\n" +
            "d+5s5lwJaNsm8jDZK0y4wLbp6E9afegihlqrlVOUG0DoHS/BKAvP+Mr+UlH7qR2N3HMXAgMBAAEC\n" +
            "gYEAg6iifDOuwD73775zosGHRWhVc3vXpPpUrRE9wCws8Gb1lkO5Wf3ZpsFjxvNBpxrnWoJs1Ajn\n" +
            "tVGKcuVWdmjQeq9q/djiG5BSvu2vtNDtGNRtSiOtTAwIM+Av7AKkP3XUqdzoebPOKkDXXhwIcKUf\n" +
            "VDt5UvQ4LLTD3ZgcnMPb5kECQQDHJZre35RgjdpeXRe0CJ6f1XqQwP+LrPtfMMJga1RbppF8M1+k\n" +
            "yYlymT75YKgqSsm4WitvDWS55CDtaZrACjy5AkEArQoMCFg+BWbaksohth4q4GtSOCJ/0xoj+3UJ\n" +
            "RcDnklBgQ6jOs0lTU8jJ8IEyFGQFiLGz6fT2/1YLPioVvZJmTwJBAKKTgpE8OSdx5rluijFBcC3P\n" +
            "25Vc2cIvX69gYO7R8DY6Dz8zuXsPxJO3o392dxK/p1pG0nqAlqBjKrZmphzsvpECQGmxP1RBgfCO\n" +
            "uGb8q8aveoUFSH0dJXJt/xhyji1a/Jc0HPh2vXppCUqd1Crg3xPxXCf4UupORCgGCGv6DLl0GKUC\n" +
            "QEMXQ/o9/s4KEzy+8zswuwozwEBY/bDJiLSeJ5i6qAfn7KduWVNJ6j7FRP1ZXEw33I28SfBk1a9d\n" +
            "/C+n41crQ80=";

    @Test
    public void CommonTest3() throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        map.put("123", "456");
//        System.out.println(map.size());
//        System.out.println(Math.floorDiv(-9, 4));
//        System.out.println(11 >> 2);
//        System.out.println(Math.floorMod(7, 4));
//        System.out.println(Math.floorMod(-7, 4));
//        System.out.println(Math.floorMod(7, -4));
//        System.out.println(Math.floorMod(-7, -4));
//        System.out.println(9 ^ -7);

//        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
//        concurrentHashMap.put("jim", "bob");
//        System.out.println(DateUtil.getYearMonth());

//        List<Callable<String>> callables = new ArrayList<>();
//        for (int i = 0; i<100; i++) {
//
//            callables.add(new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    Thread.sleep(100);
//                    System.out.println(Thread.currentThread() + "--->>" + atomicInteger.getAndIncrement());
//                    return "OK";
//                }
//            });
//            pool.submit(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread() + "--->>" + atomicInteger.getAndIncrement());
//                }
//            });
//        }
//        pool.invokeAll(callables);
//        int i;
//        for (i = 0; i < 10; i++) {
//            System.out.println("hello-->" + i);
//        }
//        System.out.println(i);

//        String certainDate = DateUtil.getCertainDate(-2);
//        String settleDate = DateUtil.getCertainDate(certainDate, 1);
//        System.out.println(certainDate + "---->" + settleDate);
//        System.out.println(20 & 536870911);
//        int COUNT_BITS = Integer.SIZE - 3;
//        int CAPACITY = (1 << COUNT_BITS) - 1;
//        System.out.println(COUNT_BITS);
//        System.out.println(CAPACITY);

//        System.out.println(new Integer(10).toString());

//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("您好：<br><br>");
//        stringBuilder.append(DateUtil.getStringFromDate(new Date(), DateUtil.FORMAT_DATETIME));
//        stringBuilder.append("-");
//        stringBuilder.append(DateUtil.getStringFromDate(new Date(), DateUtil.FORMAT_DATETIME));
//        stringBuilder.append("时间段内存在未清算交易，麻烦进行处理");
//        System.out.println(stringBuilder.toString());
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("a".getBytes());
//        int read = byteArrayInputStream.read();
//        System.out.println(read);
//        System.out.println(new Random().nextInt(9999));

//        String descrypt = AESUtil.decrypt("kx9eiReI4fCPTrBK", "hU4I2aZgPxWukVXdzepj+JGXRkTS/Vgyh5iH1LcE6/yRTnpZbcFWDwD9bT5RsEnaZ10XzQpr5ZGkiqFiDnfvMBX3vrYuJcF6P65EkQXmIGskZGjHm+rVgvZTlaimdTF6");
//        System.out.println(descrypt);
//        System.out.println(ThreadLocalRandom.current().nextInt(10));
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
//        String s = Strings.commonPrefix(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), "retg");
//        System.out.println(s);


//        System.out.println(Thread.currentThread().getContextClassLoader().getResource("a.txt").getPath());
//        System.out.println(TestDemo.class.getClassLoader().getResource("1.jpg").getPath());
        System.out.println(System.getProperty("user.dir"));
    }




    public int getRes() {
        int a = 5;
        try {
            a = 10;
            return a;
        } finally {
            a = 20;
        }
    }
}
