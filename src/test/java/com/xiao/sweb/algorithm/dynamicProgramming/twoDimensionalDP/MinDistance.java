package com.xiao.sweb.algorithm.dynamicProgramming.twoDimensionalDP;

/**
 * 72. 编辑距离
 */
public class MinDistance {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (m * n == 0) {
            return m + n;
        }
        int[][] D = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int i = 0; i < m + 1; i++) {
            D[0][i] = i;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int leftDown = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    leftDown += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, leftDown));
            }
        }
        return D[n][m];

    }
}
