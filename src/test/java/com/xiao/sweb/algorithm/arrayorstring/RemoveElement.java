package com.xiao.sweb.algorithm.arrayorstring;

import java.util.Arrays;

/**
 * 27. 移除元素
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val){
        int i = 0;
        int n = nums.length;
        for (int j = 0; j < n; j++) {
            if (nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        for (int j = i; j < n; j++) {
            nums[j] = 0;
        }
        return i;
    }
}
