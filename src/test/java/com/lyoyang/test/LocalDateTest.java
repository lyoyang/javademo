package com.lyoyang.test;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import com.google.common.io.ByteStreams;
import com.lyoyang.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.server.ByteBufferInputStream;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class LocalDateTest {


    @Test
    public void ff() {

        LocalDateTime parse = LocalDateTime.parse("2020-10-30 10:23:12", DateTimeFormatter.ofPattern(DateUtil.FORMAT_DATETIME));

        System.out.println(parse.toLocalDate().isEqual(LocalDateTime.now().toLocalDate()));

        String str = "https://detail.tmall.com/item.htm?spm=a1z10.11316-b-s.0.0.a375e096vPhkbo&id=625884081164";
        String s = StringUtils.substringAfter(str, "id=");
        System.out.println(s);


    }

    @Test
    public void ddd() {
        System.out.println(71/70);
    }

}
