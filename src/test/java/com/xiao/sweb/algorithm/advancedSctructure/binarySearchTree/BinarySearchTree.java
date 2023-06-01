package com.xiao.sweb.algorithm.advancedSctructure.binarySearchTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        // 构建闭区间[1, n]组成的BST
        return build(1, n);
    }

    private List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();
        if (lo > hi) {
            res.add(null);
            return res;
        }

        // 穷举root所以节点可能
        for (int i = lo; i <= hi; i++) {
            List<TreeNode> leftTree = build(lo, i - 1);
            List<TreeNode> rightTree = build(i + 1, hi);

            // 给root节点穷举所有左右子树的组合
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    // i作为根节点root的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(right);
                }
            }
        }
        return res;
    }
}
