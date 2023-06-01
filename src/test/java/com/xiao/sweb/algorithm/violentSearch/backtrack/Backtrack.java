package com.xiao.sweb.algorithm.violentSearch.backtrack;

import java.util.LinkedList;

public class Backtrack {

    public void backtrack(int[] nums, LinkedList<Integer> track) {
        if (nums.length == track.size()){
            return;
        }
        Integer[] a = track.toArray(new Integer[0]);
        for (int i = 0; i < nums.length; i++) {
            if (track.contains(nums[i])){
                continue;
            }
            track.add(nums[i]);
            backtrack(nums, track);
            track.removeLast();
        }
    }
}
