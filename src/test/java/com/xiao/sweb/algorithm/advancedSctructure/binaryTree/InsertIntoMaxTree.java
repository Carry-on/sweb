package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

/**
 * 998. 最大二叉树 II
 */
public class InsertIntoMaxTree {

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode cur = root;
        while (cur != null) {
            if (val > cur.val) {
                if (parent == null) {
                    return new TreeNode(val, root, null);
                }
                TreeNode node = new TreeNode(val, cur, null);
                parent.right = node;
                return root;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }
        parent.right = new TreeNode(val);
        return root;
    }
}
