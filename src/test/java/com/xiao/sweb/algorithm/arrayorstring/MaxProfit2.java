package com.xiao.sweb.algorithm.arrayorstring;

/**
 * 122. 买卖股票的最佳时机 II
 */
public class MaxProfit2 {
//    public int maxProfit(int[] prices) {
//        int n = prices.length;
//        int[][] dp = new int[n][2];
//        dp[0][0] = 0;
//        dp[0][1] = -prices[0];
//        for (int i = 1; i < n; i++) {
//            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
//            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
//        }
//        return dp[n - 1][0];
//    }

//    public int maxProfit(int[] prices){
//        int n = prices.length;
//        int dp0 = 0, dp1 = -prices[0];
//        for (int i = 1; i < n; i++) {
//            int newDp0 = Math.max(dp0, dp1 + prices[i]);
//            int newDp1 = Math.max(dp1, dp0 - prices[i]);
//            dp0 = newDp0;
//            dp1 = newDp1;
//        }
//        return dp0;
//    }

    public int maxProfit(int[] prices) {
        int ans = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }
}
