package com.xiao.sweb.algorithm.arrayorstring;

/**
 * 26. 删除有序数组中的重复项
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums){
        if (nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int fast = 1, slow = 1;
        while (fast < n){
            if (nums[fast] != nums[fast - 1]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
