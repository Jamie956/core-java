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
