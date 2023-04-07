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
