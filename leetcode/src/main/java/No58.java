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
