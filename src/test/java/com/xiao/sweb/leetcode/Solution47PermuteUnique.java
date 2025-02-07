package com.xiao.sweb.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II
 */
public class Solution47PermuteUnique {
    boolean[] vis;
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> perm = new ArrayList<>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, res, perm, 0);
        return res;
    }

    public void backtrack(int[] nums, List<List<Integer>> res, List<Integer> perm, int idx){
        if (idx == nums.length){
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || (i > 0 && nums[i] == nums[i-1] && !vis[i-1])){
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, res, perm, idx+1);
            vis[i] = false;
            perm.remove(idx);
        }
    }
}
