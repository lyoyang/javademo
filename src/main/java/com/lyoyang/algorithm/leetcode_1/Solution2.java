package com.lyoyang.algorithm.leetcode_1;

/**
 * @author: yangbing
 * @Date: 2019/7/24 17:41
 * @Description:
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 */
public class Solution2 {


    public static int method1(int[] nums) {
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (tmp == nums[i]) {
                //todo
            }
        }
        return nums.length;
    }


    public static void main(String[] args) {

    }




}
