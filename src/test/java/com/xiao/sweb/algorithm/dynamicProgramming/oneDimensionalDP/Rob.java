package com.xiao.sweb.algorithm.dynamicProgramming.oneDimensionalDP;

import java.util.Arrays;

/**
 * 198. 打家劫舍
 */
public class Rob {
//    private int[] memo;
//    public int rob(int[] nums){
//        memo = new int[nums.length];
//        Arrays.fill(memo, -1);
//        // 强盗从第 0 间房⼦开始抢劫
//        return dp(nums, 0);
//    }
//
//    // 返回 dp[start..] 能抢到的最⼤值
//    private int dp(int[] nums, int start) {
//        if (start >= nums.length){
//            return 0;
//        }
//        // 避免重复计算
//        if (memo[start]  != -1){
//            return memo[start];
//        }
//        int res = Math.max(dp(nums, start + 1), nums[start] + dp(nums, start + 2));
//        // 记⼊备忘录
//        memo[start] = res;
//        return res;
//
//    }

    public int rob(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        if (n == 1){
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            int temp = second;
            second = Math.max(second, first + nums[i]);
            first = temp;
        }
        return second;
    }

}
