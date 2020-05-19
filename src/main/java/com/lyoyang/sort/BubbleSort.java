package com.lyoyang.sort;

/**
 * 冒泡排序
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * 稳定排序
 * 原地排序
 *
 */
public class BubbleSort {


    public static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i -1; j++) {
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        return arr;
    }


    /**
     * 动态规划
     * @param arr
     * @return
     */
    public static int[] bubbleSortOptimize(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    flag = false;
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] data = new int[]{34,3,5,7,7,8,1,0,6,12};
//        bubbleSort(data);
        bubbleSortOptimize(data);
        for (int i : data) {
            System.out.print(i + " ");
        }
    }

}
