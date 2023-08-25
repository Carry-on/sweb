package com.xiao.sweb.algorithm.dynamicProgramming.oneDimensionalDP;

/**
 * 53. 最大子数组和
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int res = nums[0];
        for(int x : nums){
            pre = Math.max(pre + x , x);
            res = Math.max(res, pre);
        }
        return res;
    }
}
