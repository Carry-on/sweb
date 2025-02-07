package com.xiao.sweb.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 46. 全排列
 */
public class Solution46Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> output = new ArrayList<>();
        for (int num : nums){
            output.add(num);
        }
        backtrack(nums.length, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first){
        if (first == n) {
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrack(n, output, res, first + 1);
            Collections.swap(output, first, i);
        }
    }
}
