package com.xiao.sweb.algorithm.dynamicProgramming.twoDimensionalDP;

import java.util.List;

/**
 * 120. 三角形最小路径和
 */
public class MinimumTotal {
//    public int minimumTotal(List<List<Integer>> triangle) {
//        int n = triangle.size();
//        int[][] f = new int[n][n];
//        f[0][0] = triangle.get(0).get(0);
//        for (int i = 1; i < n; i++) {
//            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
//            for (int j = 1; j < i; j++) {
//                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j] + triangle.get(i).get(j));
//            }
//            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
//        }
//        int minTotal = f[n - 1][0];
//        for (int i = 1; i < n; i++) {
//            minTotal = Math.min(minTotal, f[n - 1][i]);
//        }
//        return minTotal;
//    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[2][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            int curr = i % 2;
            int prev = 1 - curr;
            f[curr][0] = f[prev][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                f[curr][j] = Math.min(f[prev][j - 1], f[prev][j]) + triangle.get(i).get(j);
            }
            f[curr][i] = f[prev][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[(n - 1) % 2][0];
        for (int i = 1; i < n; i++) {
            minTotal = Math.min(minTotal, f[(n - 1) % 2][i]);
        }
        return minTotal;

    }
}
