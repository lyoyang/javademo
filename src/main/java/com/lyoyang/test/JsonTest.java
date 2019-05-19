package com.lyoyang.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Auther: yangbing
 * @Date: 2019/1/22 10:20
 * @Description:
 */
public class JsonTest {


    @Test
    public void test_parse() {
        String str = "[{\"type\":\"MERCHANT_ID\",\"account\":\"1495577562\",\"amount\":2,\"description\":\"现在支付分账\",\"result\":\"SUCCESS\",\"finish_time\":\"20180912113038\"},{\"type\":\"MERCHANT_ID\",\"account\":\"1512673471\",\"amount\":889,\"description\":\"解冻给分账方\",\"result\":\"SUCCESS\",\"finish_time\":\"20180912113038\"}]";
        JSONArray objects = JSON.parseArray(str);
        Object obj = objects.get(1);
        System.out.println(obj.toString());
        JSONObject jsonObject = JSONObject.parseObject(obj.toString());
        System.out.println(jsonObject.getString("description"));
        System.out.println(jsonObject.getBigDecimal("amount"));
    }

    @Test
    public void test() {

        BigDecimal b1 = new BigDecimal(2);
        BigDecimal b2 = new BigDecimal(7);
        BigDecimal b3 = new BigDecimal(3);
        if(!(BigDecimal.ZERO.equals(b2) && b1.equals(b3))) {
            System.out.println("待平账");
        } else {
            System.out.println("已平账");
        }

    }


}
