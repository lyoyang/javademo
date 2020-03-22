package com.lyoyang.sort;

/**
 *希尔排序
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 非稳定排序
 * 原地排序
 */

public class ShellSort {


    public static int[] shellSort(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        int n = arr.length;
        for (int gap = n/2; gap >0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                insertI(arr, i, gap);
            }
        }
        return arr;
    }

    private static void insertI(int[] arr, int index, int gap) {
        int tmp = arr[index];
        int k;
        for (k = index-gap; k >=0 && tmp < arr[k]; k-=gap) {
            arr[k+gap] = arr[k];
        }
        arr[k+gap] = tmp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{12,11,10,1,9,8,7,6};
        shellSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
