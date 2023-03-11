package com.leetcode;

/*
读题：输入一个整数数组，这个数组每个元素都会出现2次，但是其中有一个数字只出现1次，输出这个数字

思路：去除相同的数字，剩下的就是答案，利用异或性质，比如: a^b^a^b^c=0^0^c=c
 */
public class No136 {
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
        System.out.println(singleNumber(new int[]{1}));
    }
}
