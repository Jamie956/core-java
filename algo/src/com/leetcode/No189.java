package com.leetcode;

//https://leetcode-cn.com/problems/rotate-array/
public class No189 {
    public void rotate(int[] nums, int k) {
        /*
        1234567, k=2

        12345(rotate) 67->54321 67
        5432167(rotate) -> 7612345
        76(rotate) 12345 -> 6712345
        */

        k %= nums.length;
        reverse(nums, 0, nums.length - 1 - k);
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
    }

    public static void reverse(int[] nums, int h, int t) {
        while (h < t) {
            swap(nums, h, t);
            h++;
            t--;
        }
    }

    public static void swap(int[] nums, int h, int t) {
        int temp = nums[t];
        nums[t] = nums[h];
        nums[h] = temp;
    }
}
