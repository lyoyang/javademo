package com.lyoyang.sort;

/**
 * 插入排序
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 稳定排序
 * 原地排序
 *
 */

public class InsertSort {


    public static int[] insertSort(int[] arr) {
       if (arr == null || arr.length < 2) {
           return arr;
       }
       int length = arr.length;
       for (int i = 1; i < length; i++) {
           int tmp = arr[i];
           int k = i - 1;
           while (k >=0 && arr[k] > tmp) {
               k--;
           }
           for (int j = i; j > k + 1; j--) {
               arr[j] = arr[j-1];
           }
           arr[k+1] = tmp;
       }
       return arr;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{12,11,10,1,9,8,7,6};
        insertSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }


}
