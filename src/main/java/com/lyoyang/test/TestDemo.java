package com.lyoyang.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lyoyang.entity.CalculationDto;
import com.lyoyang.entity.Student;
import com.lyoyang.entity.User;
import com.lyoyang.enums.ChannelTypeMappingEnum;
import com.lyoyang.utils.AccountAgeUtil;
import com.lyoyang.utils.AmountUtils;
import com.lyoyang.utils.CSVUtils;
import com.lyoyang.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        WorkingHourReqDto reqDto = new WorkingHourReqDto();
        reqDto.setUserCd("01");
        ArrayList<WorkingHourDetailReqDto> list = new ArrayList<>();
        WorkingHourDetailReqDto r1 = new WorkingHourDetailReqDto();
        r1.setWorkingDate("2019-05-12");
        r1.setWorkingHour("1.2");

        WorkingHourDetailReqDto r2 = new WorkingHourDetailReqDto();
        r2.setWorkingDate("2019-05-12");
        r2.setWorkingHour("1.2");

        WorkingHourDetailReqDto r3 = new WorkingHourDetailReqDto();
        r3.setWorkingDate("2019-05-12");
        r3.setWorkingHour("8.7");

        WorkingHourDetailReqDto r4 = new WorkingHourDetailReqDto();
        r4.setWorkingDate("2019-05-13");
        r4.setWorkingHour("1.2");

        WorkingHourDetailReqDto r5 = new WorkingHourDetailReqDto();
        r5.setWorkingDate("2019-05-13");
        r5.setWorkingHour("1.2");
        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);
        reqDto.setDetailList(list);
        Map<String, BigDecimal> workMap = new HashMap<>();
        for(WorkingHourDetailReqDto detailReqDto : reqDto.getDetailList()) {
            if(new BigDecimal("8").compareTo(new BigDecimal(detailReqDto.getWorkingHour())) < 0) {
                System.out.println("工时范围[0-8]");
            }
            if(workMap.get(detailReqDto.getWorkingDate()) == null) {
                workMap.put(detailReqDto.getWorkingDate(), BigDecimal.ZERO);
            }
            workMap.put(detailReqDto.getWorkingDate(), new BigDecimal(detailReqDto.getWorkingHour()).add(workMap.get(detailReqDto.getWorkingDate())));
        }
        Iterator<Map.Entry<String, BigDecimal>> iterator = workMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, BigDecimal> entry = iterator.next();
            if(new BigDecimal("8").compareTo(entry.getValue()) < 0) {
                System.out.println("总工时必须小于等于8小时");
            }
        }
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
//        BigDecimal tmp = (new BigDecimal("100").subtract(new BigDecimal("20"))).multiply(new BigDecimal("0.7")).setScale(0, BigDecimal.ROUND_HALF_UP);
//        BigDecimal m = new BigDecimal("2000").multiply((new BigDecimal("0.002").subtract(new BigDecimal("0.001"))));
//        System.out.println(tmp.add(m));
//        System.out.println(new BigDecimal("8").compareTo(new BigDecimal("8.0")));
        System.out.println(BigDecimal.valueOf(1L).toPlainString());
    }



    @Test
    public void tetsHHH() throws Exception {
//        System.out.println(Float.valueOf("10.0").intValue());
//        System.out.println(new BigDecimal("1.9").setScale(0, BigDecimal.ROUND_HALF_UP));
        String str = "{\"yl_account_trans\":{\"amount\":\"200\",\"chTransId\":\"123\",\"createTime\":\"2019-05-23 12:23:12\",\"fee\":\"12\",\"feeShoulder\":\"123\",\"mchId\":\"445454df\",\"mchOrderNo\":\"35454545454545\",\"payerAccName\":\"jim\"}}";
//        Map map = JSON.parseObject(str, Map.class);
//        System.out.println(map.toString());

//        LocalDateTime now = LocalDateTime.now();
//        String dateStr = DateUtil.getDateStr(now, DateUtil.FORMAT_DATE);
//        System.out.println(dateStr);
        ArrayList<String> list = new ArrayList<>();
        list.add("123");
        list.add("345");
        boolean b = list.stream().anyMatch(s -> s.equals("12345"));
//        System.out.println(b);
//        System.out.println(AmountUtils.changeF2Y(0L).toString());
//        ChannelTypeMappingEnum channelTypeMappingEnum = ChannelTypeMappingEnum.buildByTypeCode("90");
//        System.out.println(ChannelTypeMappingEnum.ALIPAY.equals(channelTypeMappingEnum));
//        System.out.println(LocalDateTime.now().toLocalDate());
//        long d = 12l;
//        System.out.println((int)d);
//        Instant parse1 = Instant.parse("201902051234");
//        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy年MM月");
//        LocalDate parse = LocalDate.parse("201902051234");
//        System.out.println(formatters);
//        Map<String, String> accountAge = AccountAgeUtil.getAccountAge(3, LocalDate.now());
//        System.out.println(accountAge);
//        String format = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
//        System.out.println(format);
//        String sss = "2019,2018";
//        String[] split = sss.split(",");
//        System.out.println("2019-09-25".substring(0,7));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
//        String beginDate = LocalDate.parse("2019-06-12").with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ISO_DATE);
//        System.out.println(beginDate);
//        String endDate = LocalDate.parse("2019-06-12").with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.ISO_DATE);
//        System.out.println(endDate);
//        Map<String, LongSummaryStatistics> params = new HashMap<>();
//        LongSummaryStatistics longSummaryStatistics = params.get("123");
//        System.out.println("2019-05-23".substring(5,"2019-05-23".length()));
//        String beginDate = LocalDate.parse("2019-05-23").with(TemporalAdjusters.firstDayOfYear()).format(DateTimeFormatter.ofPattern("yyyy-MM"));
//        System.out.println(beginDate);
//        String endDate = LocalDate.parse("2019-05-23").with(TemporalAdjusters.lastDayOfYear()).format(DateTimeFormatter.ofPattern("yyyy-MM"));
//        System.out.println(endDate);

//        Date dateFromString = DateUtil.getDateFromString("2019-09", DateUtil.FORMAT_YEARMONTH);
//        Instant instant = dateFromString.toInstant();
//        ZoneId zoneId = ZoneId.systemDefault();
//        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
//        System.out.println(localDate.toString());
//        String firstTimeOfDay = DateUtil.getEndTimeOfDay("2019-05-23", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        System.out.println(firstTimeOfDay);
//        System.out.println(DateUtil.getTodayMinuteBefore(1).format(DateTimeFormatter.ISO_DATE_TIME));

//        System.out.println(LocalDate.parse("20190302", DateTimeFormatter.BASIC_ISO_DATE).format(DateTimeFormatter.ofPattern("yyyy-MM")));

//        System.out.println(LocalDate.now().minusDays(1).toString());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime four = now.plusDays(4);
        Duration between = Duration.between(four, now);
        System.out.println(between.toDays());

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
        File file = new File("D:/test.csv");
        List<String> list = new ArrayList<>();
        list.add("jim");
        list.add("bob");
        list.add("nan");
        CSVUtils.exportCsv(file, list);
    }


    @Test
    public void testLocalDate() {
        List<Map<String, String>> accountAge = AccountAgeUtil.getAccountAge(30, LocalDate.now());
        System.out.println("hell0".substring(3));
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();

    }@Test


    public void testDedcimal() {
        System.out.println(BigDecimal.valueOf(400L).negate().toPlainString());
        String msg = "oh,{0} is ''a'' pig";
        String format = MessageFormat.format(msg, "lisi");
        System.out.println(format);
        System.out.println(MessageFormat.format("{0}", 12345678.9));
        System.out.println(MessageFormat.format("oh, {0, number, #.##} is a good number", Double.valueOf("3.23445")));
        Long d = 12L;
        new Long(23l);
        int[] ss = new int[] {1,2};

    }


    @Test
    public void test121() {
        int a = 3;
        if (a > 0) {
            a = 6;
        } else if (a < 0) {
            a = 90;
        } else {
            System.out.println(a);
        }
    }




    @Test
    public void testJps() throws IOException {
        int read = System.in.read();
        System.out.println(read);
    }


    public static void main(String[] args) throws IOException {
        Student student = new Student();
        student.setId(1);
        student.setUsername("jim");
        student = null;
        System.gc();
        int read = System.in.read();
        System.out.println(read);
    }













}
