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
