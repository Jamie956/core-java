package com.leetcode;

//https://leetcode-cn.com/problems/factorial-trailing-zeroes/
public class No172 {
    public static int trailingZeroes(int n) {
        /*
        5 -> 5 4 3 2 1 -> 5 (4 3 2 1) -> 120 -> 5/5=1
        */
        int ret = 0;
        while (n >= 5) {
            ret += n / 5;
            n /= 5;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(7));
    }
}
