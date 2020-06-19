package com.lyoyang.sort;

import java.util.Arrays;

/**
 * @author: Brian
 * @Date: 2020/6/19 14:47
 * @Description:
 */
public class BobbleSort {

    public static int[] bobbleSort(int[] num) {
        if (num == null) return null;
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length - i - 1; j++) {
                if (num[j] > num[j+1]) {
                    int tmp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = tmp;
                }
            }
        }
        return num;
    }

    public static int[] bobbleSort2(int[] num) {
        if (num == null) return null;
        for (int i = 0; i < num.length; i++) {
            boolean flag = true;
            for (int j = 0; j < num.length - i - 1; j++) {
                if (num[j] > num[j+1]) {
                    flag = false;
                    int tmp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = tmp;
                }
            }
            if (flag) break;
        }
        return num;
    }


    public static void main(String[] args) {
        int[] num = new int[] {4, 2, 12, 1, 45, 23};
        int[] num2 = new int[] {1, 2, 36, 7, 3, 23};
//        print(bobbleSort(num));
        print(bobbleSort2(num2));
    }

    private static void print(int[] num) {
        Arrays.stream(num).forEach(i -> System.out.print(i + ","));
    }

}
