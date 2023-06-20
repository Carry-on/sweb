package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1104. 二叉树寻路
 */
public class PathInZigZagTree {

    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> path = new ArrayList<>();
        int row = 1, rowStart = 1;
        while (rowStart * 2 < label) {
            row++;
            rowStart *= 2;
        }
        if (row % 2 == 0) {
            label = getReverse(label, row);
        }
        while (row > 0) {
            if (row % 2 == 0){
                path.add(getReverse(label, row));
            }else{
                path.add(label);
            }
            row--;
            label >>= 1;
        }
        Collections.reverse(path);
        return path;
    }

    private int getReverse(int label, int row) {
        return (1 << row - 1) + (1 << row) - 1 - label;
    }
}
