package com.lyoyang.test;

import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;
import com.lyoyang.utils.AESUtil;
import com.lyoyang.utils.DateUtil;
import org.junit.Test;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("jim", "bob");
        System.out.println(DateUtil.getYearMonth());
        List<Callable<String>> callables = new ArrayList<>();
        for (int i = 0; i<100; i++) {
            callables.add(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread() + "--->>" + atomicInteger.getAndIncrement());
                    return "OK";
                }
            });
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + "--->>" + atomicInteger.getAndIncrement());
                }
            });
        }
        pool.invokeAll(callables);
        int i;
        for (i = 0; i < 10; i++) {
            System.out.println("hello-->" + i);
        }
        System.out.println(i);

        System.out.println(20 & 536870911);
        int COUNT_BITS = Integer.SIZE - 3;
        int CAPACITY = (1 << COUNT_BITS) - 1;
        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY);
        String descrypt = AESUtil.decrypt("kx9eiReI4fCPTrBK", "hU4I2aZgPxWukVXdzepj+JGXRkTS/Vgyh5iH1LcE6/yRTnpZbcFWDwD9bT5RsEnaZ10XzQpr5ZGkiqFiDnfvMBX3vrYuJcF6P65EkQXmIGskZGjHm+rVgvZTlaimdTF6");
        System.out.println(descrypt);
        System.out.println(ThreadLocalRandom.current().nextInt(10));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        String s = Strings.commonPrefix(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")), "retg");
        System.out.println(s);
    }


    @Test
    public void test() {
        int index = 12;
//        System.out.println(index++);
        System.out.println(++index);
    }



    @Test
    public void testWait() {
        Object o = new Object();
        synchronized (o) {
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("success");
            o.notify();
        }
    }


    @Test
    public void getProperty() {

        Stopwatch started = Stopwatch.createStarted();
        int times = 10000*10000;
        int a = 0;
        for (long i = 0; i < times; i++) {
            a = 9999%1024;
        }
        System.out.println("cost time:" + started.stop());
        int b = 0;
        Stopwatch started1 = Stopwatch.createStarted();
        for (long i = 0; i < times; i++) {
            b = 9999&(1024-1);
        }
        System.out.println("cost time:" + started1.stop());
        System.out.println(a + "," + b);
    }

    @Test
    public void haha() {
        List<String> list = Arrays.asList("java", "php", "scala", "go", "python");
//        ArrayList<String> list = new ArrayList<>();
//        list.add("java");
//        list.add("php");
//        list.add("scala");
//        list.add("go");
//        list.add("python");
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).equals("go")) {
//                list.remove(list.get(i));
//            }
//        }

        Persion p = new Kids();
        p.play();

        Animal animal = new Sparrow();
        animal.run();


    }





}
