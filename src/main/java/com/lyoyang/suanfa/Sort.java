package com.lyoyang.suanfa;

/**
 * 排序
 */
public class Sort {


    public static void bubbleSort(int[] nums, int n) {
        if (n == 1) return;
        for (int i = 0; i<n; i++) {
            boolean flag = false;
            for (int j = 0; j<n-i-1; j++) {
                if (nums[j] > nums[j+1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = tmp;
                    flag = true;
                }
            }
//            提前退出循环
            if (!flag) break;
        }
    }

    /**
     * 插入排序
     * @param nums
     * @param length
     */
    public static void insertSort(int[] nums, int length) {
        if (length <=1) return;
        for (int i = 1; i<length; i++) {
            int value = nums[i];
            int j = i - 1;
            for (; j>=0; j--) {
                if (nums[j]>value) {
                    nums[j+1] = nums[j];
                } else break;
            }
            nums[j+1] = value;
        }
    }

    /**
     * 归并排序
     * @param nums
     * @param begin
     * @param end
     */
    public static void mergeSort(int[] nums, int begin, int end) {
        if (begin >= end) return;
        int mid = begin + (end - begin)/2;
        mergeSort(nums, begin, mid);
        mergeSort(nums, mid+1, end);
        merge(nums, begin, mid, end);

    }

    public static void merge(int[] nums, int begin, int middle, int end) {
        int i = begin;
        int j = middle + 1;
        int[] tmp = new int[end+1];
        for (int k=begin; k<=end; k++) {
            tmp[k] = nums[k];
        }
        for (int k=begin; k<=end; k++) {
            if (i>middle) nums[k] = tmp[j++];
            else if (j>end) nums[k] = tmp[i++];
            else if (tmp[i] > tmp[j])  nums[k] = tmp[j++];
            else nums[k] = tmp[i++];
        }
    }

    /**
     * 快排
     * @param nums
     * @param low
     * @param height
     */
    public static void quickSort(int[] nums, int low, int height) {
        if (height <= low) return;
        int middle = getMiddle(nums, low, height);
        quickSort(nums, low, middle-1);
        quickSort(nums, middle+1, height);
    }


    public static int getMiddle(int[] nums, int low, int height) {
        int i = low;
        int p = height;
        for (int k = low; k <= height; k++) {
            if (nums[k] < nums[p]) {
                int tmp = nums[k];
                nums[k] = nums[i];
                nums[i] = tmp;
                i++;
            }
        }
        int tmp = nums[p];
        nums[p] = nums[i];
        nums[i] = tmp;
        return  i;
    }




    public static void main(String[] args) {
        int[] nums = {1,34,12,4,5,5,9};
        int[] nums2 = {1,3,12,13,15,25,39};
//        bubbleSort(nums2, nums2.length);
//        insertSort(nums2, nums2.length);
//        mergeSort(nums, 0, nums.length-1);
        int[] randomArray = getRandomArray(50);
        print(randomArray);
        quickSort(randomArray, 0, randomArray.length-1);
        print(randomArray);
    }

    public static void print(int[] nums) {
        for (int i = 0; i<nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }


    public static int[] getRandomArray(int number) {
        int[] nums = new int[number];
        for (int j = 0; j < number; j++) {
            int tmp = (int) (Math.random()*100 + 1);
            nums[j] = tmp;
        }
        return nums;
    }

}
