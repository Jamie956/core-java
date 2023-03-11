package com.leetcode;

/*
66. Plus One
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
You may assume the integer does not contain any leading zero, except the number 0 itself.
*/
public class No66 {
    /*
    输入数组为非空数组，元素为非负整数，
    每个元素共同组成一个整数，给这个整数加1，
    数组每个元素都是单个数字

    加1后是否需要进位
    1.不需要进位，返回结果
    2.需要进位，读取下位元素并+1，再判断是否需要进位
        如果数组全部元素都要进位，首位要补1，末尾补0
     */
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length-1; i >= 0; i--) {
            digits[i] += 1;
            if (digits[i] != 10) {
                return digits;
            }
            digits[i]=0;
        }

        int[] newDigits = new int[digits.length+1];
        newDigits[0]=1;

        return newDigits;
    }

    public static void main(String[] args) {
        //[9] 9+1
        //[9,9,9] 999+1
        //[1,9,9] 199+1
        //[0] 0+1
        //[1] 1+1
        //[2,2] 22+1

//        int[] digits = {9};
        int[] digits = {9,9,9};
//        int[] digits = {1, 9, 9};
//        int[] digits = {0};
//        int[] digits = {1};
//        int[] digits = {2,2};

        int[] s = plusOne(digits);
        for (int i : s) {
            System.out.println(i);
        }
    }
}
