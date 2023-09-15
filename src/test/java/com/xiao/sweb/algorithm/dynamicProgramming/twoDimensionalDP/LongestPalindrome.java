package com.xiao.sweb.algorithm.dynamicProgramming.twoDimensionalDP;

/**
 * 5. 最长回文子串
 */
public class LongestPalindrome {
    // 动态规划
//    public String longestPalindrome(String s){
//        int n = s.length();
//        if (n < 2){
//            return s;
//        }
//        int maxLen = -1;
//        int begin = 0;
//        // dp[i][j] 表示 s[i..j] 是否是回文串
//        boolean[][] dp = new boolean[n][n];
//        for (int i = 0; i < n; i++) {
//            dp[i][i] = true;
//        }
//        char[] arr = s.toCharArray();
//        // 先枚举子串长度
//        for (int L = 2; L <= n; L++) {
//            // 枚举左边界，左边界的上限设置可以宽松一些
//            for (int i = 0; i < n; i++) {
//                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
//                int j = L + i -1;
//                if (j >= n){
//                    break;
//                }
//                if (arr[i] != arr[j]){
//                    dp[i][j] = false;
//                }else {
//                    if (j - i < 3){
//                        dp[i][j] = true;
//                    }else {
//                        dp[i][j] = dp[i+1][j-1];
//                    }
//                }
//
//                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
//                if(dp[i][j] && j - i + 1 > maxLen){
//                    maxLen = j - i + 1;
//                    begin = i;
//                }
//            }
//        }
//        return s.substring(begin, begin + maxLen);
//    }

    // 中心扩展算法
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int n = s.length();
        int start = 0, end = 0;
        for (int i = 0; i < n; i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }
}
