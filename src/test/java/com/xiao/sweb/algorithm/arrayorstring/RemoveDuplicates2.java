package com.xiao.sweb.algorithm.arrayorstring;

/**
 * 80. 删除有序数组中的重复项 II
 */
public class RemoveDuplicates2 {
    public int removeDuplicates(int[] nums){
        int n = nums.length;
        if (n <= 2){
            return n;
        }
        int fast = 2, slow = 2;
        while (fast < n){
            if (nums[slow - 2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }
}
