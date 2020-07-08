package com.lyoyang.algorithm;

/**
 * @author: Brian
 * @Date: 2020/7/8 17:15
 * @Description:
 */
public class HuiWenNum {

    public static void main(String[] args) {
        System.out.println(isHuiwen(20));
        System.out.println(isHuiwen2(20));
    }


    private static boolean isHuiwen(int i) {
        if (i < 0 || (i % 10 == 0 && i != 0)) {
            return false;
        }
        int reverseNum = 0;
        while (i > reverseNum) {
            reverseNum = reverseNum * 10 + i % 10;
            i = i / 10;
        }
        return i == reverseNum || i == reverseNum / 10;
    }


    private static boolean isHuiwen2(int i) {
        int tmp = i;
//        if (i < 0 || (i % 10 == 0 && i != 0)) {
//            return false;
//        }
        StringBuilder reverseNum = new StringBuilder();
        while (i > 0) {
            reverseNum.append(i % 10);
            i = i /10;
        }
        return tmp == Integer.parseInt(reverseNum.toString());
    }



}
