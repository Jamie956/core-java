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
