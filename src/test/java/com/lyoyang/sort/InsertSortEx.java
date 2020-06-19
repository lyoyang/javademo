package com.lyoyang.sort;

import java.util.Arrays;

/**
 * @author: Brian
 * @Date: 2020/6/19 17:12
 * @Description:
 */
public class InsertSortEx {


    public static int[] insertSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int k = i - 1;
            while (k >= 0 && nums[k] > tmp) k--;
            for (int j = i; j > k + 1; j--) {
                nums[j] = nums[j-1];
            }
            nums[k+1] = tmp;
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {23, 1, 2, 12, 4, 5, 65,44,2};
        int[] nums2 = new int[] {10,9,8,7,6,5,4,3,2,1};
        int[] nums3 = new int[] {1, 4, 12, 6, 7, 34, 4, 4};
        print(insertSort(nums3));
    }


    private static void print(int[] num) {
        Arrays.stream(num).forEach(i -> System.out.print(i + ","));
    }


}
