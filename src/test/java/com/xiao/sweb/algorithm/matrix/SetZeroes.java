package com.xiao.sweb.algorithm.matrix;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * 73. 矩阵置零
 */
public class SetZeroes {
    //    public void setZeroes(int[][] matrix) {
//        int rows = matrix.length, columns = matrix[0].length;
//        boolean[] r = new boolean[rows];
//        boolean[] c = new boolean[columns];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                if (matrix[i][j] == 0) {
//                    r[i] = c[j] = true;
//                }
//            }
//        }
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                if (r[i] || c[j]) {
//                    matrix[i][j] = 0;
//                }
//            }
//        }
//    }
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length, columns = matrix[0].length;
        boolean col0 = false;
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
            }
            for (int j = 1; j < columns; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0) {
                matrix[i][0] = 0;
            }
        }
    }
}
