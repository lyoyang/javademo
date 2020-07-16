package com.lyoyang.offfer;

/**
 * @author: Brian
 * @Date: 2020/7/16 18:23
 * @Description:
 */
public class Solution7 {


    public static void main(String[] args) {
        System.out.println(fibonacci(5));
    }

    public static long fibonacci(int n) {
        long result = 0;
        long preOne = 0;
        long preTwo = 1;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        for (int i = 2; i <= n; i++) {
            result = preOne + preTwo;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }

}
