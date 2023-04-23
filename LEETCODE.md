



# 5

```java
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
        0  1  2  3  4  5
    0  T  F  F     F
    1  -  T  F  F
    2  -  -  T  T  T
    3  -  -  -  T  F
    4  -  -  -  -  T  F
    5  -  -  -  -  -  T
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
```

# 11

```java
import junit.framework.Assert;

public class No11ContainerWithMostWater {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        Assert.assertEquals(49, result);
    }

    // 双指针解法
    static class Solution {
        public int maxArea(int[] height) {
            int area = 0;
            int l = 0;
            int r = height.length-1;
            while (l<r) {
                area = Math.max(area, (r-l)*Math.min(height[l], height[r]));
                // 移动高度短的指针
                if (height[l] < height[r]) {
                    l++;
                } else {
                    r--;
                }
            }
            return area;
        }
    }
}
```

# 20

```java
/*
20. Valid Parentheses
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.


Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Constraints:
1 <= s.length <= 104
s consists of parentheses only '()[]{}'.

读题：输入括号字符串，输出是否合法括号
解题：栈消除
*/
public class No20 {
    public static boolean isValid(String s) {
        //栈
        Character[] stack = new Character[s.length()];
        int top = -1;

        Character[] openBracketChars = new Character[]{'(', '{', '['};
        Character[] closeBracketChars = new Character[]{')', '}', ']'};

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //如果是左括号，入栈
            if (findIndex(openBracketChars, c) > -1) {
                stack[++top] = c;
                continue;
            } else if (top > -1) {
                int index = findIndex(openBracketChars, stack[top]);
                Character cb = closeBracketChars[index];
                //栈顶元素与入栈元素是一对括号，出栈
                if (c == cb) {
                    top--;
                    continue;
                }
            }
            return false;
        }
        //栈清空，合法
        return top == -1;
    }

    /**
     * 在arr查找c，返回索引位置
     */
    private static int findIndex(Character[] arr, Character c) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("]"));
        System.out.println(isValid("["));
    }
}
```

# 21

```java
/*
21. Merge Two Sorted Lists
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
*/
public class No21 {
    /*
    合并两个有序单链，返回一个新的有序单链
    stack
    3   mergeTwoLists(null, 8) -> 8
    1   mergeTwoLists(7, 8) -> 7 8
    1   mergeTwoLists(5->7, 8) -> 5 7 8
    2   mergeTwoLists(5->7, 4->8) -> 4 5 7 8
    2   mergeTwoLists(5->7, 2->4->8) -> 2 4 5 7 8
    1   mergeTwoLists(1->5->7, 2->4->8) -> 1 2 4 5 7 8
     */

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(5, new ListNode(7, null)));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(8, null)));

        ListNode l = mergeTwoLists(l1, l2);
        System.out.println(l);
    }

}
```

# 22

```java
import java.util.ArrayList;
import java.util.List;

public class No22GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> strings = solution.generateParenthesis(3);
        System.out.println(strings);
    }

    static class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> combinations = new ArrayList<String>();
            generateAll(new char[2 * n], 0, combinations);
            return combinations;
        }

        /*
            递归枚举每一种情况

                (     )
                /\   /\
               (  ) (  )
               /\
              (
         */
        public void generateAll(char[] current, int pos, List<String> result) {
            // 每个结果的长度必然是 n*2 == current.length
            if (pos == current.length) {
                if (valid(current)) {
                    result.add(new String(current));
                }
            } else {
                // 按括号定义先左括号
                current[pos] = '(';
                // 递归全部可能的结果
                generateAll(current, pos + 1, result);
                current[pos] = ')';
                generateAll(current, pos + 1, result);
            }
        }

        // 是否符合括号的定义
        public boolean valid(char[] current) {
            int balance = 0;
            for (char c : current) {
                if (c == '(') {
                    ++balance;
                } else {
                    --balance;
                }
                if (balance < 0) {
                    return false;
                }
            }
            return balance == 0;
        }
    }
}
```

# 26

```java
/*
26. Remove Duplicates from Sorted Array
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
*/
public class No26 {
    /*
    移除有序数组的重复元素，输出移除后的数组长度，空间复杂度必须为O(1)
     */

    /**
     * 常规解法
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        int size = nums.length - 1;
        while (i < size) {
            if (nums[i] == nums[i + 1]) {
                for (int j = i + 1; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];
                }
                size--;
            } else {
                i++;
            }
        }
        return i + 1;
    }

    /**
     * 快慢指针解法
     */
    public static int removeDuplicates2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 0;
        while (fast < nums.length - 1) {
            if (nums[fast] != nums[fast + 1]) {
                slow++;
                nums[slow] = nums[fast + 1];
            }
            fast++;
        }
        return slow + 1;
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 2};
//        int[] nums = new int[]{1, 1};
//        int[] nums = new int[]{1};
        int[] nums = new int[]{};
//        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};

//        System.out.println(removeDuplicates(nums));

        System.out.println(removeDuplicates2(nums));
    }
}
```

# 28

```java
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
```

# 38

```java
/*
38. Count and Say
Given an integer n where 1 ≤ n ≤ 30, generate the n th term of the count-and-say sequence.

1.     1
2.     11
3.     21
4.     1211
5.     111221

n=5
the 1 th row must be 1
2 of row generate 11 from 1, mean that count+number
3 of row, 11->21, read the first number 1,
  recording count 1, then reading next number 1, it alse is 1, account++
  account=2, number=1, result->21
 */
public class No38 {
    /*
    统计字符串每个字符的个数
    */
    public static String countAndSay(int n) {
        String seq = "1";
        for (int i = 0; i < n-1; i++) {
            seq = countAndSayBySeq(seq);
        }
        return seq;
    }

    public static String countAndSayBySeq(String sequence) {
        String prev = "";
        int count = 0;

        StringBuilder nextSeq = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            String s = String.valueOf(sequence.charAt(i));
            if(prev.length()==0) {
                prev = s;
                count = 1;
            }
            else if (s.equals(prev)) {
                count++;
            }
            else {
                nextSeq.append(count).append(prev);
                prev = s;
                count = 1;
            }
            if (i+1 == sequence.length()) {
                nextSeq.append(count).append(prev);
            }
        }
        return nextSeq.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(6));
    }
}
```

# 39

```java
import java.util.ArrayList;
import java.util.List;

public class No39CombinationSum {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combinationSum(new int[]{2, 3, 5}, 8);
        System.out.println(lists);
    }

    static class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> combine = new ArrayList<>();
            dfs(candidates, target, ans, combine, 0);
            return ans;
        }

        public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
            if (idx == candidates.length) {
                return;
            }
            if (target == 0) {
                ans.add(new ArrayList<>(combine));
                return;
            }
            // 递归到树子叶时回溯
            dfs(candidates, target, ans, combine, idx + 1);

            if (target - candidates[idx] >= 0) {
                combine.add(candidates[idx]);
                // 直到递归出 target==0
                dfs(candidates, target - candidates[idx], ans, combine, idx);
                combine.remove(combine.size() - 1);
            }
        }
    }
}
```

# 46

```java
import java.util.ArrayList;
import java.util.List;

public class No46Permutations {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> res = solution.permute(new int[]{1, 2, 3});
        System.out.println(res);
    }

    static class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            int[] visited = new int[nums.length];
            List<Integer> tmp = new ArrayList<>();
            backtrack(res, nums, tmp, visited);
            return res;
        }

        /*
           枚举每一种情况，对于读取过元素/经过的树路径使用 visited 记录
                 1
                / \\
               1  2 3
                 / \\
                 1 2 3
         */
        private void backtrack(List<List<Integer>> res, int[] nums, List<Integer> tmp, int[] visited) {
            if (tmp.size() == nums.length) {
                res.add(new ArrayList<>(tmp));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                // 排除已经走过的路径
                if (visited[i] != 1) {
                    visited[i] = 1;

                    // 记录当前节点
                    tmp.add(nums[i]);
                    // 继续往下个节点走
                    backtrack(res, nums, tmp, visited);

                    // 清除当前节点
                    visited[i] = 0;
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }
}
```

# 48

```java
public class No48RotateImage {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        solution.rotate(matrix);
        System.out.println();
    }

    // matrix[col][n−row−1]=matrix[row][col]
    static class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            for (int i = 0; i < n / 2; ++i) {
                for (int j = 0; j < (n + 1) / 2; ++j) {
                    System.out.println("------ i="+i+" j="+j);
                    int temp = matrix[i][j];
                    System.out.println(i+","+j+" <- "+(n-j-1)+","+i);
                    matrix[i][j] = matrix[n - j - 1][i];
                    matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                    matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                    matrix[j][n - i - 1] = temp;
                }
            }
        }
    }
}
```

# 53

```java
/*
53. Maximum Subarray
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:
Input: nums = [1]
Output: 1

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23
*/
public class No53 {
    /*
    读题：给出一个整数数组，找出这样一个连续的子数组，它的各个元素和是最大的，求它的和

    动态规划解法
    nums[] = [-2,1,-3,4,-1,2,1,-5,4]
    f(0) = -2
    f(1) = max(f(0)+f(1), f(1)) = 1
    f(2) = max(f(1)+f(2), f(2)) = -2
    f(3) = max(f(2)+f(3), f(3)) = 4
    ...
     */
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int prevSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prevSum = Math.max(nums[i], nums[i] + prevSum);
            max = Math.max(prevSum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
```

# 58

```java
/*
58. Length of Last Word
Given a string s consists of upper/lower-case alphabets and empty space
characters ' ', return the length of last word
(last word means the last appearing word if we loop from left to right) in the string.
If the last word does not exist, return 0.

Example 1:
Input: s = "Hello World"
Output: 5
Explanation: The last word is "World" with length 5.

Example 2:
Input: s = "   fly me   to   the moon  "
Output: 4
Explanation: The last word is "moon" with length 4.

Example 3:
Input: s = "luffy is still joyboy"
Output: 6
Explanation: The last word is "joyboy" with length 6.
 

Constraints:
1 <= s.length <= 104
s consists of only English letters and spaces ' '.
There will be at least one word in s.
 */
public class No58 {
    /*
    输入字符串，输出字符串里最后一个单词的长度，忽略空格

    长度就是最后一个词的起始和末尾索引之差
    末尾：从最后一个不为空的元素算起，索引不能小于0

    起始：从末尾索引往前找，遇到空元素就返回长度，如果没遇到，返回整个字符的长度
     */
    public static int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                end = i;
                break;
            }
        }

        int start = 0;
        for (int i = end; i >= 0; i--) {
            if (i == 0) {
                start--;
            }
            if (s.charAt(i) == ' ') {
                start = i;
                break;
            }
        }
        return end - start;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(lengthOfLastWord("a "));
        System.out.println(lengthOfLastWord("day"));
    }
}
```

# 66

```java
/*
66. Plus One
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
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
```

# 67

```java
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
```

# 70

```java
/*
70. Climbing Stairs
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
*/
public class No70 {
    /*
    爬1级楼梯，有1种方式，1
    爬2级楼梯，有2种方式，1+1、2
    爬3级楼梯，有3种方式，1+1+1、2+1、1+2
    爬4级楼梯，有5种方式，1+1+1+1、2+2、2+1+1、1+2+1、1+1+2
    爬5级楼梯，有8种方式，1+1+1+1+1、2+2+1、1+2+2、2+1+2、2+1+1+1、1+2+1+1、1+1+2+1、1+1+1+2

    设n为阶级数，N为多少种方式，得到f(n)=N
    f(1)=1
    f(2)=2
    f(3)=3
    f(4)=5
    f(5)=8
    f(n)=f(n-1)+f(n-2)
     */

    public static int climbStairs(int n) {
        int[] dp = new int[Math.max(n, 2)];
        dp[0] = 1;
        dp[1] = 2;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(1));

//        System.out.println(climbStairs(4));
//        System.out.println(climbStairs(5));
//        System.out.println(climbStairs(6));
    }
}
```

# 78

```java
import java.util.ArrayList;
import java.util.List;

public class No78Subsets {
    public static void main(String[] args) {
//        Solution solution = new Solution();
//        List<List<Integer>> res = solution.subsets(new int[]{1, 2, 3});
//        System.out.println(res);

        Solution2 solution = new Solution2();
        List<List<Integer>> res = solution.subsets(new int[]{1, 2, 3});
        System.out.println(res);
    }

    static class Solution {
        List<Integer> tmp = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        public List<List<Integer>> subsets(int[] nums) {
            dfs(0, nums);
            return result;
        }

        public void dfs(int cur, int[] nums) {
            if (cur == nums.length) {
                result.add(new ArrayList<Integer>(tmp));
                return;
            }
            tmp.add(nums[cur]);
            dfs(cur + 1, nums);
            tmp.remove(tmp.size() - 1);
            dfs(cur + 1, nums);
        }
    }

    static class Solution2 {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        public List<List<Integer>> subsets(int[] nums) {
            for (int k = 0; k <= nums.length; k++) {
                backTrace(0, k, new ArrayList<>(), nums);
            }
            return result;
        }

        // k 限定的长度
        public void backTrace(int start, int k, ArrayList<Integer> tmp, int[] nums) {
            if (k == 0) {
                result.add(new ArrayList<>(tmp));
                return;
            }
            for (int i = start; i < nums.length; i++) {
                tmp.add(nums[i]);
                backTrace(i + 1, k - 1, tmp, nums);
                tmp.remove(tmp.size() - 1);
            }
        }
    }
}
```

# 88

```java
/*
读题：给出两个升序的整数数组，合并这个两个数组，合并后仍然是有序数组

思路：分配两个指针读取两个数组，比较读取数字，小的数字写入一个新数组，
最后这个数组就是合并后的数组
 */
public class No88 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < m && j < n) {
            if (nums1[i] > nums2[j]) {
                arr[k++] = nums2[j++];
            } else {
                arr[k++] = nums1[i++];
            }
        }

        while (i < m) {
            arr[k++] = nums1[i++];
        }

        while (j < n) {
            arr[k++] = nums2[j++];
        }

        for (int l = 0; l < arr.length; l++) {
            nums1[l] = arr[l];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        merge(nums1, 3, new int[]{2, 5, 6}, 3);

        for (int i : nums1) {
            System.out.println(i);
        }
    }
}
```

# 94

```java
import java.util.ArrayList;
import java.util.List;

/*
94. Binary Tree Inorder Traversal
Given the root of a binary tree, return the inorder traversal of its nodes' values.

Example 1:
Input: root = [1,null,2,3]
Output: [1,3,2]

Example 2:
Input: root = []
Output: []

Example 3:
Input: root = [1]
Output: [1]

Constraints:
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100

中序遍历（inorder traversal，LDR，中根遍历）：左根右
 */
public class No94 {
    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    static List<Integer> resultSet = new ArrayList<>();
    public static List<Integer> inorderTraversal(TreeNode root) {
        recursion(root);
        return resultSet;
    }

    public static void recursion(TreeNode root) {
        if(root == null) {
            return;
        }
        //一个节点没有左节点时，将节点值加入结果集，并递归右结点
        if(root.left == null) {
            resultSet.add(root.val);
            recursion(root.right);
        } else {
            //如果一个结点有左结点，递归左结点，递归后将结点值加到结果集，递归右结点
            recursion(root.left);
            resultSet.add(root.val);
            recursion(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.right = n2;
        n2.left = n3;

        List<Integer> ret = inorderTraversal(n1);
        System.out.println();
    }
}
```

# 100

```java
/*
100. Same Tree
Given two binary trees, write a function to check if they are the same or not.
 */
public class No100 {
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null) && (q == null)) {
            return true;
        }
        if ((p == null) || (q == null)) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        a1.left = a2;
        a1.right = a3;

        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(2);
        TreeNode b3 = new TreeNode(2);
        b1.left = b2;
        b1.right = b3;

        System.out.println(isSameTree(a1, b1));
    }
}
```

# 101

```java
/*
101. Symmetric Tree
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
*/
public class No101 {
    /*
        判断一棵树是否是对称
        比较左字树和右子树的根节点，递归子树的子树，叶子节点判断
     */
    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean isSymmetric(TreeNode root) {
        return isSymmetricRecursion(root, root);
    }

    private static boolean isSymmetricRecursion(TreeNode n1, TreeNode n2) {
        if ((n1 == null) && (n2 == null)) {
            return true;
        }
        if ((n1 == null) || (n2 == null)) {
            return false;
        }
        if (n1.val != n2.val) {
            return false;
        }
        return isSymmetricRecursion(n1.left, n2.right) && isSymmetricRecursion(n1.right, n2.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3, null, null), new TreeNode(4, null, null)),
                new TreeNode(2,
                        new TreeNode(4, null, null), new TreeNode(3, null, null))
        );
        System.out.println(isSymmetric(root));
    }
}
```

# 104

```java
/*
104. Maximum Depth of Binary Tree

Given a binary tree, find its maximum depth.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*/
public class No104 {
    public static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(3, null, null), new TreeNode(4, null, null)),
                new TreeNode(7,
                        new TreeNode(5, null, null), new TreeNode(6, null, null))
        );
        System.out.println(maxDepth(root));

    }
}
```

# 108

```java
/*
108. Convert Sorted Array to Binary Search Tree
Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.

Example 1:
Input: nums = [-10,-3,0,5,9]
Output: [0,-3,9,-10,null,5]
Explanation: [0,-10,5,null,-3,null,9] is also accepted:

Example 2:
Input: nums = [1,3]
Output: [3,1]
Explanation: [1,3] and [3,1] are both a height-balanced BSTs.

Constraints:
1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in a strictly increasing order.

读题：输入一个整数升序数组，转化成一个高度平衡二叉树（每个节点两棵棵子树高度相差不超过1），输出平衡二叉树的数组

解题：输入的数组是有序的，那中间位置的元素就是根节点，并以此位界限，左边递归，右边递归
 */
public class No108 {
    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        return recursion(nums, 0, nums.length - 1);
    }

    private static TreeNode recursion(int[] nums, int from, int to) {
        if (from > to) return null;
        int p = (from + to) / 2;
        TreeNode root = new TreeNode(nums[p]);
        root.left = recursion(nums, from, p - 1);
        root.right = recursion(nums, p + 1, to);
        return root;
    }

    public static void main(String[] args) {
        int[] arr = {-10, -3, 0, 5, 9};
        TreeNode treeNode = sortedArrayToBST(arr);
        System.out.println();
    }

}
```

# 111

```java
/*
111. Minimum Depth of Binary Tree
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.
 */
public class No111 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = minDepth(root.left);
        int r = minDepth(root.right);

        if (l != 0 && r != 0) {
            return Math.min(l, r) + 1;
        } else {
            //?
            return Math.max(l, r) + 1;
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n4.right = n5;

        System.out.println(minDepth(n1));
    }

}
```

# 112

```java
/*
112. Path Sum
Given the root of a binary tree and an integer targetSum,
return true if the tree has a root-to-leaf path
such that adding up all the values along the path equals targetSum.

Example 1:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.

Example 2:
Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.

Example 3:
Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths.

Constraints:
The number of nodes in the tree is in the range [0, 5000].
-1000 <= Node.val <= 1000
-1000 <= targetSum <= 1000

读题：输入二叉树和整数，判断是否存在一条二叉树路径，使得路径的和与输入整数相等

解题思路：递归每一条路径，每经过一个节点就用总和减去节点值；中止条件 1)节点为空 2)到达叶节点时，差值是否为0
 */
public class No112 {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val - sum == 0;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(8);
        TreeNode n3 = new TreeNode(11);
        TreeNode n4 = new TreeNode(13);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(2);
        TreeNode n8 = new TreeNode(1);

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n3.left = n6;
        n3.right = n7;
        n2.left = n4;
        n2.right = n5;
        n5.right = n8;

        System.out.println(hasPathSum(root, 22));
    }
}
```

# 118

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
118. Pascal's Triangle
Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

Example 2:
Input: numRows = 1
Output: [[1]]

Constraints:
1 <= numRows <= 30

读题：输入一个整数的行号，返回杨辉三角对应行号的值

解题思路：
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
1 5 10 10 5 1

例子：121的下一行
前后加0，再相加
1210
0121
0+1,1+2,2+1,1+0

numRows=1, []
numRows=2, [1]
numRows=3, [[1], [1,1]]
numRows=4, [[1], [1,1], [1,2,1]]
numRows=6, [[1], [1,1], [1,2,1], [1,3,3,1]]
*/
public class No118 {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> rows = new ArrayList<>(numRows - 1);
        rows.add(Collections.singletonList(1));
        for (int i = 0; i < numRows - 1; i++) {
            List<Integer> prevRow = rows.get(i);
            List<Integer> row = new ArrayList<>(prevRow.size() + 1);
            for (int j = 0; j < prevRow.size() + 1; j++) {
                int x = (j < prevRow.size()) ? prevRow.get(j) : 0;
                int y = (j - 1 < 0) ? 0 : prevRow.get(j - 1);
                row.add(x + y);
            }
            rows.add(row);
        }
        return rows;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
```

# 119

```java
import java.util.ArrayList;
import java.util.List;

/*
119. Pascal's Triangle II
Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

Example 1:
Input: rowIndex = 3
Output: [1,3,3,1]

Example 2:
Input: rowIndex = 0
Output: [1]

Example 3:
Input: rowIndex = 1
Output: [1,1]

Constraints:
0 <= rowIndex <= 33

Follow up: Could you optimize your algorithm to use only O(rowIndex) extra space?

1 3 3 1 0
0 1 3 3 1

0 1 3 3 1
1 3 3 1 0

1 4 6 4 1

解题思路：
求三角的第x行
数组补零，当前元素与下一个元素相加
*/
public class No119 {
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        row.add(1);
        for (int i = 0; i < rowIndex; i++) {
            for (int j = i + 1; j >= 0; j--) {
                //末尾补0
                int x = j > i ? 0 : row.get(j);
                //开头补0
                int y = j - 1 < 0 ? 0 : row.get(j - 1);
                if (j <= i) {
                    row.remove(j);
                }
                row.add(j, x + y);
            }
        }
        return row;
    }

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }
}
```

# 121

```java
//https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
public class No121 {
    public static int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][2];
        //第一天，{卖出，买入}
        dp[0] = new int[]{0, -prices[0]};

        /*
        |      | 买max          | 卖max            |
        | ---- | -------------- | ---------------- |
        | 7    | -7             | 0                |
        | 1    | max(-7, -1)=-1 | max(-7, -7+1)=-6 |
        | 5    | max(-1, -5)=-1 | max=(-6, -1+5)=4 |
        | 3    | max(-1, -3)=-1 | max=(4, -1+3)=2  |
        | 6    | max(-1, -6)=-1 | max=(2, -1+6)=5  |
        | 4    | max(-1, -4)=-1 | max=(5, -1+4)=3  |
        |      |                |                  |
         */
        for (int i = 1; i < prices.length; i++) {
            //昨天卖出的最大收益，最低价的买入+今天卖出=今天卖出的最大收益
            int x = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //最低价的买入
            int y = Math.max(dp[i - 1][1], -prices[i]);
            dp[i] = new int[]{x, y};
        }

        return dp[prices.length - 1][0];
    }

    public static void main(String[] args) {
        //7,1,5,3,6,4
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

}
```

# 122

```java
/*
122. Best Time to Buy and Sell Stock II
You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
Find and return the maximum profit you can achieve.

Example 1:
Input: prices = [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Total profit is 4 + 3 = 7.

Example 2:
Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Total profit is 4.

Example 3:
Input: prices = [7,6,4,3,1]
Output: 0
Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.

Constraints:
1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
 */
public class No122 {
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] - prices[i] > 0) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices));
    }
}
```

# 125

```java
/*
125. Valid Palindrome
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
Given a string s, return true if it is a palindrome, or false otherwise.

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.

Constraints:
1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.

读题：输入字符串，判断字符串是否对此，忽略空格、符号等
 */
public class No125 {
    private static boolean invalidChar(Character c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    public static boolean isPalindrome(String s) {
        int h = 0, t = s.length() - 1;
        while (h < t) {
            //指针跳过空格、符号等
            while (h < t && !invalidChar(s.charAt(h))) {
                h++;
            }
            while (h < t && !invalidChar(s.charAt(t))) {
                t--;
            }

            char hChar = s.charAt(h);
            char tChar = s.charAt(t);

            //char转成小写
            if (hChar >= 'A' && hChar <= 'Z') {
                hChar = (char) (hChar + 32);
            }
            if (tChar >= 'A' && tChar <= 'Z') {
                tChar = (char) (tChar + 32);
            }

            //不对称
            if (hChar != tChar) {
                return false;
            }
            h++;
            t--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
}
```

# 136

```java
/*
读题：输入一个整数数组，这个数组每个元素都会出现2次，但是其中有一个数字只出现1次，输出这个数字

思路：去除相同的数字，剩下的就是答案，利用异或性质，比如: a^b^a^b^c=0^0^c=c
 */
public class No136 {
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
        System.out.println(singleNumber(new int[]{1}));
    }
}
```

# 141

```java
import java.util.HashSet;
import java.util.Set;

/*
141. Linked List Cycle
Given head, the head of a linked list, determine if the linked list has a cycle in it.
There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
Return true if there is a cycle in the linked list. Otherwise, return false.

Example 1:
Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

Constraints:
The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.

Follow up: Can you solve it using O(1) (i.e. constant) memory?

判断一条单链是否是闭合，链的末尾指向链中的某个节点
假设链中有节点A，链尾有节点B，如果B.next==A，它就是闭合链，A==B.next
使用set储存每个节点，当前A节点已存在set，当B.next存入set时，set.has(A)==ture
*/
public class No141 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(-4);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n2;

        System.out.println(hasCycle(n1));
    }
}
```

# 144

```java
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序遍历：按照访问根节点——左子树——右子树的方式遍历这棵树
 */
public class No144PreorderTraversal {
    public static void main(String[] args) {
        TreeNode tree1 = new TreeNode(1);
        tree1.setLeft(null);
        TreeNode leaf1 = new TreeNode(2);
        leaf1.setLeft(new TreeNode(3));
        tree1.setRight(leaf1);

        Solution solution = new Solution();
        List<Integer> res = solution.preorderTraversal(tree1);
        Assert.assertEquals(1, res.get(0).intValue());
        Assert.assertEquals(2, res.get(1).intValue());
        Assert.assertEquals(3, res.get(2).intValue());

        Solution2 solution2 = new Solution2();
        List<Integer> res2 = solution2.preorderTraversal(tree1);
        Assert.assertEquals(1, res2.get(0).intValue());
        Assert.assertEquals(2, res2.get(1).intValue());
        Assert.assertEquals(3, res2.get(2).intValue());
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

    static class Solution {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            preorder(root, res);
            return res;
        }

        private void preorder(TreeNode node, List<Integer> res) {
            if (node == null) {
                return;
            }

            int val = node.val;
            TreeNode left = node.left;
            TreeNode right = node.right;
            res.add(val);
            preorder(left, res);
            preorder(right, res);
        }
    }

    static class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }

            Deque<TreeNode> stack = new LinkedList<>();
            // 作为树指针
            TreeNode node = root;
            while (!stack.isEmpty() || node != null) {
                // 树指针一直往左树移动，直到末尾
                while (node != null) {
                    res.add(node.val);
                    // 父节点入栈，用来出栈遍历右树
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                // 从栈读取父节点的右树
                node = node.right;
            }
            return res;
        }
    }

}
```

# 160

```java
/*
160. Intersection of Two Linked Lists
Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

Example 1:
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.

读题：找出两条链相交的元素
*/
public class No160 {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode c1 = headA;
        ListNode c2 = headB;
        //使2个指针处于同一起跑线
        while (c1 != c2) {
            c1 = (c1 == null) ? headB : c1.next;
            c2 = (c2 == null) ? headA : c2.next;
        }
        return c1;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(1);

        ListNode k1 = new ListNode(5);
        ListNode k2 = new ListNode(6);
        ListNode k3 = new ListNode(1);

        ListNode m1 = new ListNode(8);
        ListNode m2 = new ListNode(4);
        ListNode m3 = new ListNode(5);

        m1.next = m2;
        m2.next = m3;

        n1.next = n2;
        n2.next = m1;

        k1.next = k2;
        k2.next = k3;
        k3.next = m1;

        ListNode r = getIntersectionNode(n1, k1);
        System.out.println();
    }

}
```

# 167

```java
//https://leetcode-cn.com/problems/two-sum/
public class No167 {
    public static int[] twoSum(int[] numbers, int target) {
        int h = 0, t = numbers.length - 1;

        while (h < t) {
            //加起来大于target，末尾元素比较大，所以排除末尾元素
            if (numbers[h] + numbers[t] > target) {
                t--;
            }
            //加末尾大的数都大不过target，所以要找下一个h位置的元素
            if (numbers[h] + numbers[t] < target) {
                h++;
            }
            if (numbers[h] + numbers[t] == target) {
                return new int[]{h + 1, t + 1};
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] ret = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(ret);
    }
}
```

# 169

```java
/*
读题：数组中存在1个整数，它的个数超过数组长度的1/2，找出这个数字

解题思路：比较数字，相同的存储起来，数字不相同的互相抵消，剩下的数字就是出现次数最多的那个

总结：核心思想是不同的元素互相抵消
 */
public class No169 {
    /**
     * 栈抵消
     * 空间 O(n)
     * 时间 O(n)
     */
    public static int majorityElement(int[] nums) {
        int[] stack = new int[nums.length];
        int top = -1;
        for (int num : nums) {
            if (top == -1) {
                stack[++top] = num;
            } else if (stack[top] == num) {
                stack[++top] = num;
            } else {
                top--;
            }
        }
        return stack[0];
    }

    /**
     * 用 candidate 表示当前最多的元素，用 count 统计元素出现的次数
     *
     * 判断逻辑
     * 1.空栈时 count==0，赋值candidate
     * 2.candidate相同，更新candidate，count++
     * 3.candidate 不相等，count--
     *
     * 空间 O(n)
     * 时间 O(n)
     */
    public static int majorityElement2(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
                count++;
            } else if (candidate == num) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
//        System.out.println(t.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
//        System.out.println(t.majorityElement(new int[]{3,2,3}));
//        System.out.println(t.majorityElement(new int[]{2, 1, 1, 1, 1, 2, 2}));

        System.out.println(majorityElement2(new int[]{2, 2, 1, 1, 1, 2, 2}));
        System.out.println(majorityElement2(new int[]{3,2,3}));
        System.out.println(majorityElement2(new int[]{2, 1, 1, 1, 1, 2, 2}));
    }
}
```

# 171

```java
//https://leetcode-cn.com/problems/excel-sheet-column-number/submissions/
public class No171 {
    public static int titleToNumber(String columnTitle) {
        int len = columnTitle.length();
        int ret = 0;
        for (int i = 0; i < len; i++) {
            //26进制，每一个高位都是26的倍数
            ret += (columnTitle.charAt(i) - 'A' + 1) * Math.pow(26, len - 1 - i);
        }
        return ret;
    }

    public static void main(String[] args) {
        titleToNumber("AB");
    }
}
```

# 172

```java
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
```

# 189

```java
//https://leetcode-cn.com/problems/rotate-array/
public class No189 {
    public void rotate(int[] nums, int k) {
        /*
        1234567, k=2

        12345(rotate) 67->54321 67
        5432167(rotate) -> 7612345
        76(rotate) 12345 -> 6712345
        */

        k %= nums.length;
        reverse(nums, 0, nums.length - 1 - k);
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
    }

    public static void reverse(int[] nums, int h, int t) {
        while (h < t) {
            swap(nums, h, t);
            h++;
            t--;
        }
    }

    public static void swap(int[] nums, int h, int t) {
        int temp = nums[t];
        nums[t] = nums[h];
        nums[h] = temp;
    }
}
```

# 190

```java
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
```

# 191

```java
/*
读题：计算一个整数的二进制数的1的个数

解题思路：

●  从右到左读取二进制数，每读一个判断是否是1（x&1=x, x&0=0），如果是1计数器+1，再右移计算判断下一位
●  方法2

统计 11010010 的1 的个数
分组
11 01 00 10
统计每个分组1的个数
2 1 0 1
分组
21 01
相加
3 1
相加
4

思考：如何把 2 1 0 1 构造出来？
 11010010
&01010101
=01010000

  1101001
&01010101
=01000001

 01010000
+01000001
=10010001
分组
10 01 00 01
2 1 0 1

构造 3 1
 10010001
&00110011
=00010001

   100100
&00110011
=00100000

 00010001
+00100000
=00110001

0011 0001
3 1

构造4
 00110001
&00001111
=00000001

     0011
&00001111
=00000011

 00000001
+00000011
=00000100

4

总结：位操作特性 x&1=x, x&0=0
 */
public class No191 {
    public static int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n&1) == 1) {
                count++;
            }
            n >>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(-3));
    }
}
```

# 198

```java
/*
https://leetcode-cn.com/problems/house-robber/

题目：
不相邻的数相加得到的最大值
input: 1 2 4 1 7 8 3
output: 15

解题思路：
设OPT(i) 为i位置到0位置所有不相邻数相加的最优解

OPT(i) = max(
选i: OPT(i-2)+arr[i]
不选i: OPT(i-1)
)
 */
public class No198 {
    /*
    递归解法
    - 递归中止条件i=0和i=1
    - 递归选的情况和不选的情况

    arr 输入数组
    i   索引位置
     */
    public static int recOpt(int[] arr, int i) {
        if (i == 0) {
            return arr[0];
        } else if (i == 1) {
            return Math.max(arr[0], arr[1]);
        }
        int a = arr[i] + recOpt(arr, i - 2);
        int b = recOpt(arr, i - 1);
        return Math.max(a, b);
    }

    /*
    动态规划解法
    - 初始化数组，用来记录dp值
    - 初始位置0和1的最优解
    - 遍历计算每个i位置的最优解
     */
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (nums.length == 1) {
            return nums[0];
        }
        dp[1] = Math.max(nums[0], nums[1]);

        for (int j = 2; j < nums.length; j++) {
            //偷上上家和这家的最大收益，偷上一家的最大收益
            int max = Math.max(nums[j] + dp[j - 2], dp[j - 1]);
            dp[j] = max;
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
    }
}
```

# 206

```java
public class No206 {
    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode listNode = reverseList(head);
    }

}
```

# 208

```java
import org.junit.Assert;

public class No208PrefixTree {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertFalse(trie.search("app"));
        Assert.assertTrue(trie.startsWith("app"));
        trie.insert("app");
        Assert.assertTrue(trie.search("app"));
    }

    static class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }

}
```

# 226

```java
public class No226 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //交换两个对称位置的节点的值
    public static TreeNode invertTree(TreeNode root) {
        //终止条件
        if (root == null) return null;
        TreeNode l = invertTree(root.left);
        TreeNode r = invertTree(root.right);
        //交换左右节点
        root.left = r;
        root.right = l;
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeNode treeNode = invertTree(root);
    }
}
```

# 231

```java
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
```

# 234

```java
/*
234. Palindrome Linked List
Given the head of a singly linked list, return true if it is a palindrome.

Example 1:
Input: head = [1,2,2,1]
Output: true

Example 2:
Input: head = [1,2]
Output: false

Constraints:
The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?

读题：输入一个单链，判断是否是回文

解题思路：快慢指针将链表对半分开，并且左边的链表做反正处理，比较两条链表是否一致，一致则是回文
 */
public class No234 {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static boolean isPalindrome(ListNode head) {
        //一个元素也算回文
        if (head.next == null) {
            return true;
        }
        //快慢指针
        ListNode fast = head;
        ListNode slow = head;
        ListNode reverseList = null;
        ListNode prevSlowNode = null;
        while (fast != null && fast.next != null) {
            reverseList = slow;
            slow = slow.next;
            //快指针跳跃
            fast = fast.next.next;
            //头插反转链表
            reverseList.next = prevSlowNode;
            prevSlowNode = reverseList;
        }

        //数组长度为奇数
        if (fast != null) {
            slow = slow.next;
        }

        while (reverseList != null && slow != null) {
            if (slow.val != reverseList.val) {
                return false;
            }
            reverseList = reverseList.next;
            slow = slow.next;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(head));
    }
}
```

# 242

```java
/*
题目理解：给出2个字符串，如果2个字符串的各个字母出现次数相同，则返回true

解题思路：字母一共26个，用数组记录每个字母的出现次数。
每个字符串对应一个数组，最终比较数组是否相等

总结：字母的个数是有限的，且ASII码是递增的整数，能把字母与数组索引的槽位对应起来，
意味这数组索引能推出字母，那么不同的字母的统计数就可以存储在数组对应的索引
 */
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
```

# 268

```java
/*
读题：输入一个数组，这个数组包含范围从0到n的不重复整数，返回这个范围缺失的一个数

解题思路：

● 暴力解法，用一个数据标记存在的整数，再次遍历这个数组就知道哪个数字不存在
● O(1)空间解法，使用等比数学公式求和，用总和减去输入数组全部元素，减到最后的值就是输出

总结：常规想法做标记，另外还能利用数学公式解答，大大降低复杂度
 */
public class No268 {

    /**
     * 暴力解法，用一个数据标记存在的整数，再次遍历这个数组就知道哪个数字不存在
     */
    public static int missingNumber(int[] nums) {
        boolean[] arr = new boolean[nums.length + 1];
        for (int index : nums) {
            arr[index] = true;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!arr[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * O(1)空间解法，使用等比数学公式求和，用总和减去输入数组全部元素，减到最后的值就是输出
     */
    public static int missingNumber2(int[] nums) {
        int len = nums.length;
        int sum = (len * (len + 1)) / 2;
        for (int num : nums) {
            sum = sum - num;
        }
        return sum;
    }

    public static void main(String[] args) {
//        System.out.println(t.missingNumber(new int[]{0, 1}));
//        System.out.println(t.missingNumber(new int[]{3, 0, 1}));
//        System.out.println(t.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));

        System.out.println(missingNumber2(new int[]{0, 1}));
        System.out.println(missingNumber2(new int[]{3, 0, 1}));
        System.out.println(missingNumber2(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }
}
```

# 283

```java
/*
读题：给出一个整数数组，将数组里的0移动到数组末尾，不允许复制数组，要在原数组上修改

解题思路：

●  定义指针i遍历数组，当i读到0时，记录0的个数，当读到非0的整数时移动元素arr[i-count]=arr[i]，最后把末尾的0补齐
●  双指针划分区间，分别划分为不是0区间、0的区间和还没读取的区间；j指针在左，i指针在右；i指针移动遍历，当读取到0时移动i指针，当读取到非0时，i,j元素发生交换，移动j指针。
总结：元素移动 -> 指针，元素交换
 */
public class No283 {
    public static void moveZeroes(int[] nums) {
        int zeroCount = 0;
        int i=0;
        for(i=0;i<nums.length;i++){
            if(nums[i]==0){
                zeroCount++;
            }else{
                nums[i-zeroCount] = nums[i];
            }
        }

        if((i-zeroCount)<nums.length){
            i = i-zeroCount;
            while(i<nums.length){
                nums[i] = 0;
                i++;
            }
        }
    }

    public static void moveZeroes2(int[] nums) {
        int i=0;
        int j=0;

        for(i=0;i<nums.length;i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes2(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
```

# 338

```java
/*
338. Counting Bits
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n),
ans[i] is the number of 1's in the binary representation of i.

Example 1:
Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10

Example 2:
Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101

Constraints:
0 <= n <= 105

Follow up:
It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?

读题：输入整数n，计算0到n的每个数的二进制的1的个数，每个个数组成数组，输出个数数组
 */
public class No338 {
    /*
    解法1
    对于任意整数 x，令 x=x&(x-1)，该运算将 x的二进制表示的最后一个 1变成 0，将全部1变为0的循环次数就是1的个数。
    时间复杂度：O(nlogn)，0到n的每个整数计算，每个整数计算比特1个数O(logn)
    空间复杂度：O(1)，除了返回的数组以外，空间复杂度为常数
     */
    public static int[] countBits(int n) {
        //
        int[] arr = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int oneCount = 0;
            int j = i;
            while (j > 0) {
                //1010&1001
                j = j & (j - 1);
                oneCount++;
            }
            arr[i] = oneCount;
        }
        return arr;
    }

    //解法2：动态规划——最高有效位
    public static int[] countBits2(int n) {
        int[] arr = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            //i是2的整数次幂
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            arr[i] = arr[i - highBit] + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i : countBits(5)) {
            System.out.println(i);
        }
    }
}
```

# 437

```java
public class No437 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return paths(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    public static int paths(TreeNode root, int targetSum) {
        if (root == null) return 0;
        int count = 0;
        if (root.val == targetSum) {
            count += 1;
        }
        return count + paths(root.left, targetSum - root.val)
                + paths(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        root.right.right = new TreeNode(11);

        int i = pathSum(root, 8);
    }
}
```

# 448

```java
import java.util.ArrayList;
import java.util.List;

public class No448 {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int num : nums) {
            int index = Math.abs(num) - 1;
            //将存在的数字所在位置的元素标记
            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> disappearedNumbers = findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
    }
}
```

# 461

```java
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
```

# 538

```java
//https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
public class No538 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    public static void main(String[] args) {
        No538 t = new No538();

        TreeNode root = new TreeNode(0);
        root.right = new TreeNode(1);
        TreeNode treeNode = t.convertBST(root);
    }
}
```

# 543

```java
//https://leetcode-cn.com/problems/diameter-of-binary-tree/
public class No543 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        depth(root);
        return diameter;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = depth(root.left);
        int rightDepth = depth(root.right);
        diameter = Math.max(leftDepth + rightDepth, diameter);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int i = new No543().diameterOfBinaryTree(root);
    }
}
```

# 581

```java
//https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
public class No581 {

    public static int findUnsortedSubarray(int[] nums) {
        int l = 0, r = 0, max = nums[0], min = nums[nums.length - 1];

        for (int i = 0; i < nums.length; i++) {
            int end = nums.length - 1 - i;
            //从右到左比较，如果读到的数比右边的最大值大，更新左边界指针
            if (nums[end] <= min) {
                min = nums[end];
            } else {
                l = end;
            }
            if (max <= nums[i]) {
                max = nums[i];
            } else {
                r = i;
            }
        }
        return r > l ? r - l + 1 : 0;
    }

    public static void main(String[] args) {
        int i = findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15});
    }
}
```

# 617

```java
public class No617 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        //merge 对应结点的值
        TreeNode node = new TreeNode(root1.val + root2.val);
        node.left = mergeTrees(root1.left, root2.left);
        node.right = mergeTrees(root1.right, root2.right);
        return node;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(1);
        t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(4);
        t2.right.right = new TreeNode(7);

        TreeNode n = mergeTrees(t1, t2);
    }

}
```

# 702

```java
import junit.framework.Assert;

public class No702BinarySearch {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(4, solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        Assert.assertEquals(-1, solution.search(new int[]{-1,0,3,5,9,12}, 2));
        Assert.assertEquals(0, solution.search(new int[]{5}, 5));
    }

    static class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = (right - left) / 2 + left;
                int num = nums[mid];

                if (num == target) {
                    return mid;
                }
                if (num < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return -1;
        }
    }
}
```

# 912

```java
/*
读题：整数数组升序排序

解题思路：

● 快速排序：找最后一个元素做支点，从左开始i指针遍历，判断小于支点的元素在左区间，
判断大于支点的元素放在中间区间，未遍历元素放在右区间，由j指针记录中区间左边界，再递归子区间。

 */
public class No912 {
    public static int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }

    public static void quickSort(int[] nums, int l, int r) {
        if(l<r){
            int pivot = partition(nums, l, r);
            quickSort(nums, l, pivot-1);
            quickSort(nums, pivot+1, r);
        }
    }

    public static int partition(int[] nums, int l, int r) {
        int j = l;
        for (int i = l; i < r; i++) {
            if (nums[i]<nums[r]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j++;
            }
        }
        int temp = nums[j];
        nums[j] = nums[r];
        nums[r] = temp;
        return j;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{3, 8, 1, 2, 5, 4};
//        int j = t.partition(nums, 0, 5);
//        for (int num : nums) {
//            System.out.println(num);
//        }
//        System.out.println("--");
//        System.out.println(j);


//        int[] nums = new int[]{3, 8, 1, 2, 5, 4};
        int[] nums = new int[]{5,1,1,2,0,0};
        sortArray(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
```













