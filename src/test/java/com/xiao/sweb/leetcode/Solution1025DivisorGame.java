package com.xiao.sweb.leetcode;

/**
 * 1025. 除数博弈
 */
public class Solution1025DivisorGame {
    public boolean divisorGame(int n) {
//        return n%2 == 0;
        boolean[] f = new boolean[n+5];
        f[1] = false;
        f[2] = true;
        for (int i = 3; i <= n; i++) {
            for (int j = 1 ; j < i; j++) {
                if ((i % j) == 0 && !f[i-j]){
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }
}
/*
2 1          true
3 1 1        false
4 1 1 1      true
5 1 1 1 1 1  false
6 3 1 1 1    true
7 1 3 1 1 1  false
8 1 1 3 1 1 1 true
12
dp[i] = !dp[n-x]
 */