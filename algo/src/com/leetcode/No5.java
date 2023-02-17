package com.leetcode;

import static junit.framework.Assert.assertEquals;

public class No5 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        assertEquals(solution.longestPalindrome("cabbac"), "cabbac");
        assertEquals(solution.longestPalindrome("cabbad"), "abba");
        assertEquals(solution.longestPalindrome("a"), "a");
        assertEquals(solution.longestPalindrome("abba"), "abba");
        assertEquals(solution.longestPalindrome("abca"), "a");
        assertEquals(solution.longestPalindrome("abbc"), "bb");

    }

    static class Solution {
        public String longestPalindrome(String s) {
            int len = s.length();
            // 一个字母是回文串
            if (len < 2) {
                return s;
            }
            int start = 0;
            int maxLen = 1;
            char[] chars = s.toCharArray();
            boolean[][] dp = new boolean[len][len];

            for (int l = 1; l <= len; l++) {
                for (int i = 0; i < len; i++) {
                    // j - i + 1 = l
                    int j = l + i - 1;
                    if (j >= len) {
                        break;
                    }
                    if (i == j) {
                        dp[i][j] = true;
                        continue;
                    }
                    // 动态规划方程：dp[i][j] = s[i]==s[j] and (dp[i+1][j-1] or j-i+1<3)
                    if (chars[i] != chars[j]) {
                        dp[i][j] = false;
                    } else {
                        // 动态规划边界
                        if (j - i + 1 < 3) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i + 1][j - 1];
                        }
                    }

                    if (dp[i][j] && l > maxLen) {
                        maxLen = l;
                        start = i;
                    }
                }
            }
            return s.substring(start, start + maxLen);
        }
    }
}

