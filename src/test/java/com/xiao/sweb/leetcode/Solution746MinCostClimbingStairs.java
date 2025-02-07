package com.xiao.sweb.leetcode;

/**
 * 746. 使用最小花费爬楼梯
 */
public class Solution746MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int prev = 0, curr = 0;
        for (int i = 2; i < n; i++) {
            int next = Math.min(prev + cost[i-2], curr + cost[i-1]);
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
