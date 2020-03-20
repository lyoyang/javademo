package com.lyoyang.sort;

/**
 * 冒泡排序
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

    public static void main(String[] args) {
        int[] data = new int[]{34,3,5,7,7,8,1,0,6,12};
        bubbleSort(data);
        for (int i : data) {
            System.out.print(i + " ");
        }
    }

}
