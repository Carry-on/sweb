package com.xiao.sweb.algorithm.advancedSctructure.binarySearchTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 */
public class InorderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !q.isEmpty()) {
            while (root != null) {
                q.offer(root);
                root = root.left;
            }
            root = q.poll();
            if (root == p) {
                return prev;
            }
            prev = root;
            root = root.right;
        }
        return null;
    }
}
