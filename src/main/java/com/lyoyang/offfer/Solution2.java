package com.lyoyang.offfer;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * 在一个二维数组中， 每一行都按照从左到右递增的顺序排序， 每一列
 * 都按照从上到下递增的顺序排序。 请完成一个函数， 输入这样的一个二维数组和一
 * 个整数， 判断数组中是否含有该整数
 */

public class Solution2 {


    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,2,3,4},{3,4,5,6},{5,6,7,8}};
        assertThat(find1(arr, 3), equalTo(true));
        assertThat(find2(arr, 3), equalTo(true));
    }


    /**
     * 双指针 时间复杂度O(mn) 空间复杂度O(1)
     * @param array
     * @param target
     * @return
     */
    public static boolean find1(int[][] array, int target) {
        if (array == null || array.length == 0) {
            return false;
        }
        int row = 0;
        int column = array[0].length - 1;
        while (row < array.length && column >= 0) {
            if (array[row][column] == target) {
                return true;
            }
            if (array[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }


    /**
     * 二分法
     * 时间复杂度O（logmn） 空间复杂度O（1）
     * @param array
     * @param target
     * @return
     */
    public static boolean find2(int[][] array, int target) {
        if (array == null || array.length == 0) {
            return false;
        }
        int left = 0;
        int right = array.length * array[0].length -1;
        int col = array[0].length;
        while (left <= right) {
            int mid = (left + right) / 2;
            int value = array[mid / col][mid % col];
            if (value == target) {
                return true;
            } else if (value > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }



}
