package com.xiao.sweb.leetcode;

/**
 * 509. 斐波那契数
 */
public class Solution509Fib {
    public int fib(int n) {
        int p=0, q=0, r=1;
        for (int i = 1; i < n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
