package com.lyoyang.algorithm.leetcode_1;

import java.util.Arrays;

/**
 * 两数之和
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int tmp = target - nums[i];
                if (tmp == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 4, 5, 8};
        int[] ints = solution.twoSum(nums, 5);
        Arrays.stream(ints).forEach(System.out::println);
    }

}
