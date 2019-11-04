package com.lyoyang.suanfa;

import java.util.Arrays;

/**
 * 排序
 */
public class Sort {


    public static void bubbleSort(int[] nums, int n) {
        if (n == 1) return;
        for (int i = 0; i<n; i++) {
            boolean flag = false;
            for (int j = 0; j<n-i-1; j++) {
                if (nums[j] > nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    flag = true;
                }
            }
//            提前退出循环
            if (!flag) break;
        }
    }

    /**
     * 插入排序
     * @param nums
     * @param length
     */
    public static void insertSort(int[] nums, int length) {
        if (length <=1) return;
        for (int i = 1; i<length; i++) {
            int value = nums[i];
            int j = i - 1;
            for (; j>=0; j--) {
                if (nums[j]>value) {
                    nums[j+1] = nums[j];
                } else break;
            }
            nums[j+1] = value;
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,34,12,4,5,5,9};
        int[] nums2 = {1,3,12,13,15,25,39};
//        bubbleSort(nums2, nums2.length);
        insertSort(nums2, nums2.length);
        print(nums);
    }

    public static void print(int[] nums) {
        for (int i = 0; i<nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
    }


}
