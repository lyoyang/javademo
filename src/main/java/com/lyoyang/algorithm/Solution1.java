package com.lyoyang.algorithm;

/**
 * @author: yangbing
 * @Date: 2019/7/17 14:36
 * @Description:
 * 盛最对水的容器：
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution1 {

    /**
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * @param height
     * @return
     */
    public static int method1(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, Math.min(height[i], height[j])*(j - i));
            }
        }
        return maxArea;
    }

    public static int method2(int[] height) {
        int maxArea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxArea;
    }


    public static void main(String[] args) {
        int[] heignts = {1, 3, 5, 7, 12, 21, 4};
//        System.out.println(method1(heignts));
        System.out.println(method2(heignts));
    }








}
