package com.leetcode;

/*
读题：返回二进制

解题思路：对半分成高位和低位，高低位交换位置，再对高位对半切分和低位对半切分，如此类推；
与低位全1高位全0数字做按位与获取低位；将高位右移长度的1/2，再做按位与获取高位。
总结：分而治之 + 位与 交换
 */
public class No190 {
    public static int reverseBits(int n) {
        int m_16 = 0x0000ffff;//0000 0000 0000 0000 1111 1111 1111 1111
        int m_8 = 0x00ff00ff;//0000 0000 1111 1111 0000 0000 1111 1111
        int m_4 = 0x0f0f0f0f;//0000 1111 0000 1111 0000 1111 0000 1111
        int m_2 = 0x33333333;//0011 0011 0011 0011 0011 0011 0011 0011
        int m_1 = 0x55555555;//0101 0101 0101 0101 0101 0101 0101 0101

        int a = ((n & m_16) << 16) + ((n >>> 16) & m_16);
        int b = ((a & m_8) << 8) + ((a >>> 8) & m_8);
        int c = ((b & m_4) << 4) + ((b >>> 4) & m_4);
        int d = ((c & m_2) << 2) + ((c >>> 2) & m_2);
        int e = ((d & m_1) << 1) + ((d >>> 1) & m_1);
        return e;
    }

    public static void main(String[] args) {
        int i = reverseBits(67);
        System.out.println(i);
        int i1 = reverseBits(i);
        System.out.println(i1);
    }
}
