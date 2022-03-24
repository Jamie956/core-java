package com.sort;

/**
 * 选择排序
 */
public class SelectionSort {
    /**
     * 找出数组arr前n位中最大元素的位置
     */
    private static int finMaxPos(int[] arr, int n) {
        int max = arr[0];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
                pos = i;
            }
        }
        return pos;
    }

    /**
     * 与最大元素交换
     */
    public static void selectionSort(int[] arr, int n) {
        while (n > 1) {
            int pos = finMaxPos(arr, n);
            //swap
            int temp = arr[pos];
            arr[pos] = arr[n - 1];
            arr[n - 1] = temp;
            n--;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 3, 8, 2, 9};
        selectionSort(arr, arr.length);
        for (int e : arr) {
            System.out.println(e);
        }
    }
}
