package com.lyoyang.test;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LocalDateTimeTest {


    @Test
    public void testCalculate() {
        LocalDateTime past = LocalDateTime.parse("2021-01-21 03:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        System.out.println(Duration.between(past, LocalDateTime.now()).toHours());
        Date pastDate = Date.from( past.atZone(ZoneId.systemDefault()).toInstant());
        String formatBetween = DateUtil.formatBetween(pastDate, new Date(), BetweenFormatter.Level.MINUTE);
        System.out.println(formatBetween);

        System.out.println(new BigDecimal("0.02000").stripTrailingZeros().doubleValue());
        System.out.println(new BigDecimal("0.04000").stripTrailingZeros().toPlainString());
        System.out.println("测试号".substring(0, 1));
    }


    @Test
    public void getBaseRepository() {
        String simpleName = TempVo.class.getSimpleName();
        simpleName = simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1) + "Repository";
        System.out.println(simpleName);
    }


}
