package com.xiao.sweb.leetcode;

/**
 * 2555. 两个线段获得的最多奖品
 */
public class Solution2555MaximizeWin {
    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int res = 0;
        int[] dp = new int[n+1];
        for (int left=0, right=0; right < n; right ++){
            while(prizePositions[right] - prizePositions[left] > k){
                left++;
            }
            res = Math.max(res, right-left+1 + dp[right]);
            dp[right+1] = Math.max(right-left + 1, dp[right]);
        }
        return res;
    }
}
