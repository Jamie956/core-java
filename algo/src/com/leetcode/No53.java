package com.leetcode;

/*
53. Maximum Subarray
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:
Input: nums = [1]
Output: 1

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
*/
public class No53 {
    /*
    读题：给出一个整数数组，找出这样一个连续的子数组，它的各个元素和是最大的，求它的和

    动态规划解法
    nums[] = [-2,1,-3,4,-1,2,1,-5,4]
    f(0) = -2
    f(1) = max(f(0)+f(1), f(1)) = 1
    f(2) = max(f(1)+f(2), f(2)) = -2
    f(3) = max(f(2)+f(3), f(3)) = 4
    ...
     */
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int prevSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prevSum = Math.max(nums[i], nums[i] + prevSum);
            max = Math.max(prevSum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
