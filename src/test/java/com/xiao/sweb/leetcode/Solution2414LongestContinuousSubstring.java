package com.xiao.sweb.leetcode;

/**
 * 2414. 最长的字母序连续子字符串的长度
 */
public class Solution2414LongestContinuousSubstring {
    public int longestContinuousSubstring(String s) {
        int ans = 0;
        int i=0, j=0;
        char cur, next;
        cur = s.charAt(i);
        for(j=1; j<s.length(); j++){
            next = s.charAt(j);
            if (next-cur == 1){
                cur = next;
            }else {
                ans = Math.max(ans, j-i);
                i = j;
                cur = next;
            }
        }
        ans = Math.max(ans, j-i);
        return ans;
    }
}
