package com.leetcode;

/*
https://leetcode-cn.com/problems/house-robber/

题目：
不相邻的数相加得到的最大值
input: 1 2 4 1 7 8 3
output: 15

解题思路：
设OPT(i) 为i位置到0位置所有不相邻数相加的最优解

OPT(i) = max(
选i: OPT(i-2)+arr[i]
不选i: OPT(i-1)
)
 */
public class No198 {
    /*
    递归解法
    - 递归中止条件i=0和i=1
    - 递归选的情况和不选的情况

    arr 输入数组
    i   索引位置
     */
    public static int recOpt(int[] arr, int i) {
        if (i == 0) {
            return arr[0];
        } else if (i == 1) {
            return Math.max(arr[0], arr[1]);
        }
        int a = arr[i] + recOpt(arr, i - 2);
        int b = recOpt(arr, i - 1);
        return Math.max(a, b);
    }

    /*
    动态规划解法
    - 初始化数组，用来记录dp值
    - 初始位置0和1的最优解
    - 遍历计算每个i位置的最优解
     */
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length == 1) {
            return nums[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);

        for (int j = 2; j < nums.length; j++) {
            //偷上上家和这家的最大收益，偷上一家的最大收益
            int max = Math.max(nums[j] + dp[j - 2], dp[j - 1]);
            dp[j] = max;
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
    }
}
