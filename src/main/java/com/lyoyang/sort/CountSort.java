package com.lyoyang.sort;

/**
 * 计数排序 k表示临时数组的大小
 * 时间复杂度：O(n+k)
 * 空间复杂度：O(k)
 * 稳定排序
 * 非原地排序
 * 局限性：
 * 1、当数列最大跟最小值差距过大时，不适合计数排序
 * 2、当数列元素不是整数时，不适合计数排序
 *
 */

public class CountSort {


    public static int[] countSort(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        int n = arr.length;
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int[] tmp = new int[max + 1];
        for (int i = 0; i < n; i++) {
            tmp[arr[i]]++;
        }
        int k = 0;
        for (int i = 0; i <= max; i++) {
            for (int j = tmp[i]; j > 0; j--) {
                arr[k++] = i;
            }
        }
        return arr;
    }


    /**
     * 优化版本
     * @param arr
     * @return
     */
    public static int[] countSort2(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        int max= arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        int d = max - min;
        int[] tmpArray = new int[d + 1];
        for (int i = 0; i < arr.length; i++) {
            tmpArray[arr[i] - min] ++;
        }

        int sum = 0;
        for (int i = 0; i < tmpArray.length; i++) {
            sum += tmpArray[i];
            tmpArray[i] = sum;
        }

        int[] sortedArray = new int[arr.length];
        for (int j = arr.length - 1; j >= 0; j--) {
            sortedArray[tmpArray[arr[j] - min - 1]] = arr[j];
            tmpArray[arr[j] - min]--;
        }
        return sortedArray;
    }



    public static void main(String[] args) {
        int[] arr = new int[]{12,11,10,1,9,8,7,6};
//        countSort(arr);
        countSort2(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }


}
