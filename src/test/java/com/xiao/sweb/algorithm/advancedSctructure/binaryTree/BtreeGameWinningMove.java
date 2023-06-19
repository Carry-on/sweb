package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

/**
 * 1145. 二叉树着色游戏
 */
public class BtreeGameWinningMove {
    TreeNode xNode;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        find(root, x);
        int leftSize = getSubTreeSize(xNode.left);
        if (leftSize >= (n - 1) / 2) {
            return true;
        }
        int rightSize = getSubTreeSize(xNode.right);
        if (rightSize >= (n + 1) / 2) {
            return true;
        }
        int remain = n - 1 - leftSize - rightSize;
        return remain >= (n + 1) / 2;
    }

    private int getSubTreeSize(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + getSubTreeSize(node.left) + getSubTreeSize(node.right);
    }

    private void find(TreeNode node, int x) {
        if (xNode != null || node == null) {
            return;
        }
        if (node.val == x) {
            xNode = node;
            return;
        }
        find(node.left, x);
        find(node.right, x);
    }
}
