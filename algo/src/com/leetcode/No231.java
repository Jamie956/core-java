package com.leetcode;

/*
读题：已知一个整数，如果这个数是2的倍数返回true，否则false

解题思路：1.如果是2的倍数，可以一直除2，最后得到1；
2.2的倍数的数二进制是高位1，其余都是0，得到规律 n&(n-1)==0

总结：转化为二进制问题
 */
public class No231 {
    /**
     * 1.如果是2的倍数，可以一直除2，最后得到1；
     */
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while ((n % 2) == 0) {
            n /= 2;
        }
        return n == 1;
    }

    /**
     * 2.2的倍数的数二进制是高位1，其余都是0，得到规律 n&(n-1)==0
     */
    public static boolean isPowerOfTwo2(int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {

//        System.out.println(t.isPowerOfTwo(1));
//        System.out.println(t.isPowerOfTwo(16));
//        System.out.println(t.isPowerOfTwo(3));

        System.out.println(isPowerOfTwo2(1));
        System.out.println(isPowerOfTwo2(16));
        System.out.println(isPowerOfTwo2(3));
    }
}
