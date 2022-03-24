package com.leetcode;

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
