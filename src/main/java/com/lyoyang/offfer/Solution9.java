package com.lyoyang.offfer;

/**
 * 输入一个整数， 输出该数二进制表示中 1 的个数。 其中负数用补码表
 * 示
 * 思路： a&(a-1)的结果会将 a 最右边的 1 变为 0， 直到 a = 0， 还可以先将 a&1 !=
 * 0， 然后右移 1 位， 但不能计算负数的值
 */
public class Solution9 {


    public static void main(String[] args) {
        System.out.println(numberOf1(42));
    }


    public static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }




}
