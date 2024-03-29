package com.xiao.sweb.algorithm.dynamicProgramming.twoDimensionalDP;

import java.util.Arrays;

/**
 * 188. 买卖股票的最佳时机 IV
 */
public class MaxProfit4 {
//    private int maxProfit(int k, int[] prices){
//        int n = prices.length;
//        if (n == 0){
//            return 0;
//        }
//        k = Math.max(k, n/2);
//        int[][] buy = new int[n][k + 1];
//        int[][] sell = new int[n][k + 1];
//        buy[0][0] = -prices[0];
//        sell[0][0] = 0;
//        for (int i = 1; i <= k; i++) {
//            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
//        }
//        for (int i = 1; i < n; i++) {
//            buy[i][0] = Math.max(buy[i-1][0], sell[i-1][0] - prices[i]);
//            for (int j = 1; j <= k; j++) {
//                buy[i][j] = Math.max(buy[i-1][j], sell[i-1][j] - prices[i]);
//                sell[i][j] = Math.max(sell[i-1][j], buy[i-1][j-1] + prices[i]);
//            }
//        }
//        return Arrays.stream(sell[n-1]).max().getAsInt();
//    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        k = Math.min(k, n / 2);
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i <= k; i++) {
            buy[i] = sell[i] = Integer.MIN_VALUE / 2;
        }
        for (int i = 1; i < n; i++) {
            buy[0] = Math.max(buy[0], sell[0] - prices[i]);
            for (int j = 1; j <= k; j++) {
                buy[j] = Math.max(buy[j], sell[j] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j - 1] + prices[i]);
            }
        }
        return Arrays.stream(sell).max().getAsInt();
    }

    public static void main(String[] args) {
        int a = 123;
        String binary = Integer.toBinaryString(a);
        System.out.println(binary);
    }
}
