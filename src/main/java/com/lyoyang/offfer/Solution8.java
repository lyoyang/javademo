package com.lyoyang.offfer;

/**
 * @author: Brian
 * @Date: 2020/7/16 18:51
 * @Description:
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一
 * 个 n 级的台阶总共有多少种跳法
 */
public class Solution8 {

    public static void main(String[] args) {
        System.out.println(jumpFloor(4));
    }

    public static int jumpFloor(int n) {
        if (n < 3) {
            return n;
        }
        int result = 0;
        int preOne = 2;
        int preTwo = 1;
        for (int i = 3; i <= n; i++) {
            result = preOne + preTwo;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }

}
