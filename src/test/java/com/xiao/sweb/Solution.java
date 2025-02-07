package com.xiao.sweb;

public class Solution {
//    public int accountBalanceAfterPurchase(int purchaseAmount) {
//        int tensDigit = Math.round((float) purchaseAmount /10);
//        int actualAmount = tensDigit * 10;
//        return 100 -actualAmount;
//    }
public static void main(String[] args) {

}
    public int findClosestNumber(int[] nums) {
        int ans = nums[0];
        int prev = nums[0];
        int diff = Math.abs(nums[0]);
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.abs(nums[i]);
            prev = nums[i];
            if (cur - diff == 0){
                ans = Math.max(ans, prev);
            } else if(cur - diff < 0){
                ans = nums[i];
            }
            diff = Math.min(diff, cur);
        }
        return ans;
    }
}
