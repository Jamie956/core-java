package com.leetcode;

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
