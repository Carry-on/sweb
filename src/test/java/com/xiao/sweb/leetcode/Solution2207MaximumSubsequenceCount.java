package com.xiao.sweb.leetcode;

import java.util.Objects;

/**
 * 2207. 字符串中最多数目的子序列
 */
public class Solution2207MaximumSubsequenceCount {
    public long maximumSubsequenceCount(String text, String pattern) {
        int a=0, b=0;
        int c = 0;
        for (int i=0; i<text.length(); i++){
            char cur = text.charAt(i);
            if (Objects.equals(cur, pattern.charAt(1))){
                a++;
                c+=b;
            }
            if (Objects.equals(cur, pattern.charAt(0))){
                b++;
            }

        }
        a = Math.max(a, b);
        return a + c;
    }
}
