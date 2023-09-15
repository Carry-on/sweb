package com.xiao.sweb.algorithm.dynamicProgramming.twoDimensionalDP;

/**
 * 63. 不同路径 II
 */
public class UniquePathWithObstacles {
    public int uniquePathWithObstacles(int[][] obstacleGrid){
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0){
            return 0;
        }
        int rows = obstacleGrid.length, columns = obstacleGrid[0].length;
        int[] f = new int[columns];
        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (obstacleGrid[i][j] == 1){
                    f[j] = 0;
                    continue;
                }
                if (j-1 > 0 && obstacleGrid[i][j-1] == 0){
                    f[j] += f[j-1];
                }
            }
        }
        return f[columns-1];
    }
}
