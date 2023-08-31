package com.xiao.sweb.algorithm.arrayorstring;

/**
 * 45. 跳跃游戏 II
 */
public class Jump {
    public int jump(int[] nums){
        int step = 0;
        int n = nums.length;
        int farthest = 0, end = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (end == i){
                step++;
                end = farthest;
            }
        }
        return step;
    }
}
