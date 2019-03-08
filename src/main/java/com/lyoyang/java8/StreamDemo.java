package com.lyoyang.java8;

import com.google.common.collect.Lists;
import com.lyoyang.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        ArrayList<String> strs = Lists.newArrayList("jim","tom","green","","alice","tom","green");
        System.out.println(strs);
      //筛选
        List<String> col1 = strs.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        System.out.println("筛选1--》" + col1);
        ArrayList<Integer> ints = Lists.newArrayList(1,2,13,5,6,9,34,44,23);
        List<Integer> col2 = ints.stream().filter(no -> no % 2 == 0).collect(Collectors.toList());
        System.out.println("筛选2--》" + col2);

        //去重
        List<String> col3 = strs.stream().filter(str -> !str.isEmpty()).distinct().collect(Collectors.toList());
        System.out.println("去重--》" + col3);
        //截取
        List<Integer> col4 = ints.stream().limit(3).collect(Collectors.toList());
        System.out.println("截取--》" + col4);
        //跳过 跳过流的前2个元素
        System.out.println(ints.stream().filter(no -> no % 2 != 0).collect(Collectors.toList()));
        List<Integer> col5 = ints.stream().filter(no -> no % 2 != 0).skip(2).collect(Collectors.toList());
        System.out.println("跳过--》" + col5);
        //映射  是指对流中的每个数据进行操作
        List<Integer> col6 = ints.stream().map(no -> no * 2).collect(Collectors.toList());
        System.out.println("映射--》" + col6);
        ArrayList<String> list = Lists.newArrayList();
        list.add("I am a boy");
        list.add("I love the girl");
        list.add("But the girl loves another girl");
        //将小流合并成大流
        List<String> col7 = list.stream().map(line -> line.split(" ")).flatMap(Arrays::stream).collect(Collectors.toList());
        System.out.println("小流合大流--》" + col7);
        //匹配任意元素
        boolean res1 = ints.stream().anyMatch(no -> no % 2 == 0);
        System.out.println("匹配任意元素--》" + res1);
        //匹配所有元素
        boolean res2 = ints.stream().allMatch(no -> no % 2 == 0);
        System.out.println("匹配所有元素--》" + res2);
        //是否未匹配所有元素  该操作跟allMatch相反 所有的不匹配返回true
        boolean res3 = ints.stream().noneMatch(no -> no % 2 == 0);
        System.out.println("是否未匹配任何元素--》" + res3);

        //获取任一元素
        String any = strs.stream().findAny().get();
        System.out.println("获取任一元素--》" + any);

        //获取第一个元素
        String first = strs.stream().findFirst().get();
        System.out.println("获取第一个元素--》" + first);
        /**
         * 规约
         * 归约是将集合中的所有元素经过指定运算，折叠成一个元素输出，
         * 如：求最值、平均数等，这些操作都是将一个集合的元素折叠成一个元素输出
         * 在流中，reduce函数能实现归约。
         * reduce函数接收两个参数
         * 参数1 初始值  参数二 操作的Lambda表达式
         */
        //元素求和
//        Integer sum = ints.stream().reduce(0, (n1, n2) -> n1 + n2);
        Integer sum = ints.stream().reduce(0, Integer::sum);
        System.out.println("求和--》" + sum);

        /**
         * 将普通流转换成数值流
         * StreamAPI提供了三种数值流：IntStream、DoubleStream、LongStream
         * 也提供了将普通流转换成数值流的三种方法：mapToInt、mapToDouble、mapToLong
         */
        ArrayList<User> users = Lists.newArrayList();
        User u1 = new User(12);
        User u2 = new User(11);
        User u3 = new User(19);
        User u4 = new User(34);
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        int max = users.stream().mapToInt(User::getId).max().getAsInt();
        System.out.println(max);

        //Collectors
        String collect = strs.stream().map(str -> str.toUpperCase()).collect(Collectors.joining(","));
        System.out.println("Collectors join-->" + collect);
        //Map<Integer, String> collect1 = users.stream().collect(Collectors.toMap(User::getId, User::getName));
        //进行数据集合的归并计算
        IntSummaryStatistics statistics = ints.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println("max value:" + statistics.getMax());
        System.out.println("min value:" + statistics.getMin());
        System.out.println("sum value:" + statistics.getSum());
        System.out.println("averge value:" + statistics.getAverage());
        System.out.println("num count:" + statistics.getCount());

    }
}
