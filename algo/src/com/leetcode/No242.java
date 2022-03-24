package com.leetcode;

public class No242 {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] sArr = new int[26];
        int[] tArr = new int[26];

        for (char sChar : s.toCharArray()) {
            int index = sChar - "a".charAt(0);
            sArr[index] = sArr[index] + 1;
        }

        for (char tChar : t.toCharArray()) {
            int index = tChar - "a".charAt(0);
            tArr[index] = tArr[index] + 1;
        }

        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i] != tArr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(t.isAnagram("hello", "helol"));
//        System.out.println(t.isAnagram("net", "ten"));
//        System.out.println(t.isAnagram("rat", "car"));
//        System.out.println(t.isAnagram("anagram", "nagaram"));

        System.out.println(isAnagram("nl", "cx"));

    }
}
