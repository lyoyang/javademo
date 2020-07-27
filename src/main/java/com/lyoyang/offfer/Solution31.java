package com.lyoyang.offfer;

/**
 * 输入一个整型数组， 数组中有正数也有负数， 数组中一个或连续的多
 * 个整数组成一个子数组， 求连续子数组的最大和
 * 思路：若和小于 0， 则将最大和置为当前值， 否则计算最大和
 */

public class Solution31 {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 9, -2, 3};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int sum = 0;
        int result = nums[0];
        for (int num : nums) {
            sum = sum > 0 ? sum + num : num;
            result = Math.max(sum, result);
        }
        return result;
    }

}
