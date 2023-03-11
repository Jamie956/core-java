package com.leetcode;

/*
读题：给出两个升序的整数数组，合并这个两个数组，合并后仍然是有序数组

思路：分配两个指针读取两个数组，比较读取数字，小的数字写入一个新数组，
最后这个数组就是合并后的数组
 */
public class No88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < m && j < n) {
            if (nums1[i] > nums2[j]) {
                arr[k++] = nums2[j++];
            } else {
                arr[k++] = nums1[i++];
            }
        }

        while (i < m) {
            arr[k++] = nums1[i++];
        }

        while (j < n) {
            arr[k++] = nums2[j++];
        }

        for (int l = 0; l < arr.length; l++) {
            nums1[l] = arr[l];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        merge(nums1, 3, new int[]{2, 5, 6}, 3);

        for (int i : nums1) {
            System.out.println(i);
        }
    }
}
