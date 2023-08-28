package com.xiao.sweb.algorithm.arrayorstring;

/**
 * 121. 买卖股票的最佳时机
 */
public class MaxProfit {
    public int maxProfit(int[] prices){
        int n = prices.length;
        int max = 0;
        for (int i = n-1; i >= 0 ; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int temp = prices[i] - prices[j];
               if (temp > max){
                   max = temp;
               }
            }
        }
        return max;
    }
}
