package com.leetcode;

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
