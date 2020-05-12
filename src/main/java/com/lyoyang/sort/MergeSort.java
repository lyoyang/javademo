package com.lyoyang.sort;



/**
 * 归并排序
 * 时间复杂度：O(nlongn)
 * 空间复杂度：O(n)
 * 稳定排序
 * 非原地排序
 *
 */

public class MergeSort {


    /**
     * 递归式
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int[] mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            arr = mergeSort(arr, left, mid);
            arr = mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
        return arr;
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tmp = new int[right-left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        while (i <= mid) tmp[k++] = arr[i++];
        while (j <= right) tmp[k++] = arr[j++];
        for (i = 0; i < k; i++) {
            arr[left++] = tmp[i];
        }
    }


    /**
     * 非递归式
     * @param arr
     * @return
     */
    public static int[] notRecursiveMergeSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i+=1) {
            int left = 0;
            int mid = left + i -1;
            int right = mid + i;
            while (right < n) {
                merge(arr, left, mid, right);
                left = right + 1;
                mid = left + i -1;
                right = mid + i;
            }
            if (left < n && mid < n) {
                merge(arr, left, mid, n-1);
            }
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{12,11,10,1,9,8,7,6};
//        mergeSort(arr, 0, arr.length - 1);
        notRecursiveMergeSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}
