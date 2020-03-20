package com.lyoyang.sort;

/**
 * 选择排序
 * 时间复杂度O(n^2)
 * 空间复杂度O(1)
 * 非稳定排序
 * 原地排序
 */
public class SelectorSort {


    public static int[] selectSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n -1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] data = new int[]{34,3,5,7,7,8,1,0,6,12};
        selectSort(data);
        for (int i : data) {
            System.out.print(i + " ");
        }
    }

}
