package com.lyoyang.sort;

import java.util.Arrays;

/**
 * @author: Brian
 * @Date: 2020/6/19 15:49
 * @Description:
 */
public class SelectSort {


    public static int[] selectSort(int[] nums) {
       for (int i = 0; i < nums.length - 1; i++) {
           int min = i;
           for (int j = i + 1; j < nums.length; j++) {
               if (nums[j] < nums[min]) {
                   min = j;
               }
           }
           int tmp = nums[i];
           nums[i] = nums[min];
           nums[min] = tmp;
       }
       return nums;
    }




    public static void main(String[] args) {
        int[] num = new int[] {1, 4, 12, 6, 7, 34, 4, 4};
        print(selectSort(num));
    }

    private static void print(int[] num) {
        Arrays.stream(num).forEach(i -> System.out.print(i + ","));
    }

}
