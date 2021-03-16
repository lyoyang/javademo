package com.lyoyang.algorithm.offer_59;

import java.util.Arrays;

/**
 * 滑动窗口最大值
 */
public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        int[] maxNums = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            maxNums[i] = getMax(nums, i, i + k);
        }
        return maxNums;
    }


    private int getMax(int[] nums, int start, int end) {
        int max = nums[start];
        for (int i = start; i < end; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 4, 1, 5, 12, 10, 7, 9};
        Solution solution = new Solution();
        int[] ints = solution.maxSlidingWindow(nums, 3);
        Arrays.stream(ints).forEach(System.out::println);
    }

}
