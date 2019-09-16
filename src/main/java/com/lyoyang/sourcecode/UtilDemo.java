package com.lyoyang.sourcecode;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.lyoyang.entity.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UtilDemo {


    @Test
    public void testArraySort() {
        List<User> users = ImmutableList.of(
                new User(12,1),
                new User(4,2),
                new User(23,3),
                new User(16,4)
        );
        User[] userArr = new User[users.size()];
        users.toArray(userArr);
        System.out.println("排序前：" + JSONObject.toJSONString(userArr));
        Arrays.sort(userArr, Comparator.comparing(User::getAge));
        System.out.println("排序后：" + JSONObject.toJSONString(userArr));
//        Arrays.binarySearch()
    }



}
