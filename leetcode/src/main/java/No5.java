import static org.junit.Assert.assertEquals;

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

    /*
    一个长度大于2的回文串去掉首尾字母还是回文串
    ababa -> bab

    P(i,j) 表示字符串是否是回文串，i和j是首尾字母的位置
    S[i,j] 表示字母i到j的字符串

    P(i,j)=
    ● true: Si...Sj 是回文串
    ● false:
      ○ S[i,j] 不是回文串
      ○ i>j, S[i,j] 不合法

    按照定义写出动态规划的状态转移方程：
    P(i,j)=P(i+1,j-1) and (Si==Sj)
    当s[i+1,j-1] 是回文串，且第i个字母和第j个字母相同时，得出s[i:j]是回文字符串

    动态规划边界条件：
    ● P(i,i)=true: 长度1的串是回文串
    ● P(i,i+1)=(Si==Si+1): 两个相同的字母是回文串

    example：
    cabbac
    012345
    column 代表头的位置 i，row 代表尾位置 j
    观察发现，斜边（\）是上数值对应的字符串是等长的
        0	1	2	3	4	5
    0	T	F	F		F
    1	-	T	F	F
    2	-	-	T	T	T
    3	-	-	-	T	F
    4	-	-	-	-	T	F
    5	-	-	-	-	-	T
     */
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

