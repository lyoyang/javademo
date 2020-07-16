package com.lyoyang.offfer;

/**
 * 一个栈压入元素， 而另一个栈作为缓冲， 将栈 1 的元素出栈后压入栈 2
 * 中。 也可以将栈 1 中的最后一个元素直接出栈， 而不用压入栈 2 中再出栈
 * 思路：
 * 利用二分法， 找到数组的中间元素 mid。 如果中间元素 > 数组第一个元素，
 * 在 mid 右边搜索变化点。 如果中间元素 < 数组第一个元素， 我们需要在 mid 左边
 * 搜索变化点。 当找到变化点时停止搜索， 满足 nums[mid] > nums[mid + 1]
 * （mid+1 是最小值） 或 nums[mid - 1] > nums[mid]（ mid 是最小值） 即可
 */
public class Solution6 {


    public static int minInReversingList(int[] arr) {
        if (arr == null || arr.length == 1) {
            return -1;
        }
        if (arr.length == 1 || arr[arr.length - 1] > arr[0]) {
            return arr[0];
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {

        }
    }



}
