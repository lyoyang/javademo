package com.lyoyang.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: yangbing
 * @Date: 2019/7/1 21:09
 * @Description:
 */
public class AccountAgeUtil {

    public static List<Map<String, String>> getAccountAge(int age, LocalDate localDate) {
        List<Map<String, String>> params = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 0;
        if (age != 0) {
            endIndex = age - 1;
        }
        int fomNum = 10;
        for (int i = 1; i <= fomNum; i++) {
            Map<String, String> map = new HashMap<>(1);
            String beginDate = localDate.minusDays(endIndex).format(DateTimeFormatter.BASIC_ISO_DATE) + "000000";
            String endDate = localDate.minusDays(startIndex).format(DateTimeFormatter.BASIC_ISO_DATE) + "235959";
            map.put(startIndex + "-" + endIndex, (beginDate + "," + endDate));
            startIndex = startIndex + age;
            endIndex = endIndex + age;
            params.add(map);
            if (age == 0) {
                return params;
            }
        }
        return params;
    }



}
