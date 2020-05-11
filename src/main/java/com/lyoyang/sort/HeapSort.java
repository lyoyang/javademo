package com.lyoyang.sort;


/**
 * 堆排序
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(1)
 * 原地排序
 * 非稳定排序
 */
public class HeapSort {

    /**
     * 下沉
     * @param arr
     * @param parent
     * @param length
     * @return
     */
    public static int[] downAdjust(int[] arr, int parent, int length) {
        int tmp = arr[parent];
        int child = parent * 2 + 1;
        while (child < length) {
            if (child + 1 < length && arr[child] > arr[child + 1] ) {
                child++;
            }
            if (tmp <= arr[child]) break;
            arr[parent] = arr[child];
            parent = child;
            child = child * 2 + 1;
        }
        arr[parent] = tmp;
        return arr;
    }


    public static int[] heapSort(int[] arr, int length) {
        for (int i = (length-2)/2; i>=0; i--) {
            arr = downAdjust(arr, i, length);
        }
        for (int i = length - 1; i >= 1; i--) {
            int tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            arr = downAdjust(arr, 0, i);
        }
        return arr;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{12,11,10,1,9,8,7,6,23,3,5,7,1,9};
        heapSort(arr, arr.length);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }



}
