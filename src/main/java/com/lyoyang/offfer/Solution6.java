package com.lyoyang.offfer;

import java.util.Optional;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的
 * 旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。 例如
 * 数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为 1。 NOTE：
 * 思路：
 * 利用二分法， 找到数组的中间元素 mid。 如果中间元素 > 数组第一个元素，
 * 在 mid 右边搜索变化点。 如果中间元素 < 数组第一个元素， 我们需要在 mid 左边
 * 搜索变化点。 当找到变化点时停止搜索， 满足 nums[mid] > nums[mid + 1]
 * （mid+1 是最小值） 或 nums[mid - 1] > nums[mid]（ mid 是最小值） 即可
 */
public class Solution6 {


    public static void main(String[] args) {
        //3,5,7,12,16,20,23,45
//        int[] arr = new int[] {12,16,20,23,45,2,3,4,7};
        int[] arr = new int[] {4,3};
//        Optional.of(minInReversingList(arr)).ifPresent(System.out::println);
        Optional.of(minReverseList2(arr)).ifPresent(System.out::println);
    }


    /**
     * 二分查找 时间复杂度 O(logn) 空间复杂度O(1)
     * @param arr
     * @return
     */
    public static int minInReversingList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1 || arr[arr.length - 1] > arr[0]) {
            return arr[0];
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid +1]) {
                return arr[mid + 1];
            }
            if (arr[mid] < arr[mid - 1]) {
                return arr[mid];
            }
            if (arr[mid] > arr[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


    /**
     * 时间复杂度O(logn) 空间复杂度O(1)
     * @param arr
     * @return
     */
    public static int minReverseList2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return arr[left];
    }


}
