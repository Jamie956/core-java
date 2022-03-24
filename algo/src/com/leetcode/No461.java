package com.leetcode;

//https://leetcode-cn.com/problems/hamming-distance/
public class No461 {
    public static int hammingDistance(int x, int y) {
        //x和y二进制对应的位不同时，异或结果为1
        int n = x ^ y;
        int count = 0;
        //统计1的个数
        while (n != 0) {
            count++;
            //n&(n-1) 性质，去除低位的1
            n = n & (n - 1);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }
}
