package com.lyoyang.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class IdGenerator {

    private long workerId = 0;

    @PostConstruct
    void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器 workerId: {}", workerId);
        } catch (Exception e) {
            log.warn("获取机器 ID 失败", e);
            workerId = NetUtil.getLocalhost().hashCode();
            log.info("当前机器 workerId: {}", workerId);
        }
    }

    public synchronized String batchId(int tenantId, int module) {
        String prefix = DateTime.now().toString(DatePattern.PURE_DATETIME_MS_PATTERN);
        return prefix + tenantId + module + RandomUtil.getNotSimple(3);
    }

    public String simpleUUID() {
        return IdUtil.simpleUUID();
    }

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, 1);

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workerId, long dataCenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        IdGenerator idGenerator = new IdGenerator();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
//            long transId = idGenerator.snowflakeId();
            String s = idGenerator.batchId(1, 2);
            if (list.contains(s)) {
                log.info("transId=" + s);
                break;
            } else {
                list.add(s);
            }
        }
        System.out.println(list.size());
//        System.out.println(idGenerator.batchId(1, 1));
    }

}
