package com.leetcode;

/*
28. Implement strStr()
Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/
public class No28 {
    /*
    输入字符串a,b，用b去a找是否存在，返回存在位置索引
     */
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        char c = needle.charAt(0);
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == c) {
                if ((i + needle.length()) <= haystack.length()) {
                    String substring = haystack.substring(i, i + needle.length());
                    if (substring.equals(needle)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("aaaaa", "bba"));
        System.out.println(strStr("", ""));
        System.out.println(strStr("aaa", "aaaa"));
        System.out.println(strStr("a", "a"));

    }
}
