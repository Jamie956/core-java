package com.leetcode;

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
