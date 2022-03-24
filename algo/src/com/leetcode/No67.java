package com.leetcode;

/*
67. Add Binary
Given two binary strings, return their sum (also a binary string).
The input strings are both non-empty and contains only characters 1 or 0.

读题：输入两个二进制字符串，把字符串当作数字相加，输出字符串相加结果
解题：指针读取每个位数，位数相加，分情况处理进制
*/
public class No67 {
    public static String addBinary(String a, String b) {
        StringBuilder stringBuilder = new StringBuilder();
        int carryBit = 0;
        for (int i = a.length() - 1, j = b.length() - 1; (i >= 0 || j >= 0); i--, j--) {
            int bitA = (i >= 0) ? a.charAt(i) - '0' : 0;
            int bitB = (j >= 0) ? b.charAt(j) - '0' : 0;

            //进位为0：1+1;1+0;0+0;
            //进位为1：(1+1)+1;(1+0)+1;(0+0)+1;
            int bitSum = bitA + bitB + carryBit;
            stringBuilder.append(bitSum % 2);

            if (bitSum == 0) {
                carryBit = 0;
            } else if (bitSum == 1) {
                carryBit = 0;
            } else if (bitSum == 2) {
                carryBit = 1;
            } else if (bitSum == 3) {
                carryBit = 1;
            }
        }
        if (carryBit == 1) {
            stringBuilder.append("1");
        }
        return stringBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        //100 + 10 = 110
        //100 + 100 = 1000
        //110 + 10 = 6 + 2 = 8 = 1000
        //1010 + 1011 = 10 + 11 = 21 = 1 0101
        //111 +111 = 14 = 1110

        System.out.println(addBinary("100", "10"));
        System.out.println(addBinary("100", "100"));
        System.out.println(addBinary("110", "10"));
        System.out.println(addBinary("1010", "1011"));
        System.out.println(addBinary("111", "111"));
    }

}
