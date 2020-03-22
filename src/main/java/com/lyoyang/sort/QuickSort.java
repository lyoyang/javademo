package com.lyoyang.sort;

/**
 * 快速排序
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 非稳定排序
 * 原地排序
 *
 */

public class QuickSort {



    public static int[] quickSort(int[] arr, int left, int right) {
        int center;
        if (left < right) {
//            center = partionLeft(arr, left, right);
//            center = partionRight(arr, left, right);
            center = partionBothSide(arr, left, right);
            quickSort(arr, left, center -1);
            quickSort(arr, center + 1, right);
        }
        return arr;
    }

    /**
     * 左边为主元
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partionLeft(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left + 1;
        for (int j = left + 1; j <= right; j++) {
            if (arr[j] < pivot) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
            }
        }
        arr[left] = arr[i-1];
        arr[i-1] = pivot;
        return i-1;
    }

    /**
     * 右边为主元
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int partionRight(int[] arr, int left, int right) {
       int pivot = arr[right];
       int i = left;
       for (int j = left; j < right; j++) {
           if (arr[j] < pivot) {
               int tmp = arr[j];
               arr[j] = arr[i];
               arr[i] = tmp;
               i++;
           }
       }
       arr[right] = arr[i];
       arr[i] = pivot;
       return i;
    }


    /**
     * 双向扫描
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int partionBothSide(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left + 1;
        int j = right;
        while (true) {
            while (i <= j && arr[i] <= pivot) i++;
            while (i <= j && arr[j] >= pivot) j--;
            if (i >= j)
                break;
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        arr[left] = arr[j];
        arr[j] = pivot;
        return j;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{12,11,10,1,9,8,7,6,23,3,5,7,1,9};
        quickSort(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }


}
