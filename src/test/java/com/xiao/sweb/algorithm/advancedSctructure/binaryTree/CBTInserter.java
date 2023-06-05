package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 043. 往完全二叉树添加节点
 */
public class CBTInserter {
    Queue<TreeNode> candiData;
    TreeNode root;

    public CBTInserter(TreeNode root) {
        this.candiData = new ArrayDeque<>();
        this.root = root;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (!(node.left != null && node.right != null)) {
                candiData.offer(node);
            }
        }
    }

    public int insert(int v) {
        TreeNode child = new TreeNode(v);
        TreeNode node = candiData.peek();
        int ret = node.val;
        if (node.left == null) {
            node.left = child;
        } else {
            node.right = child;
            candiData.poll();
        }
        candiData.offer(child);
        return ret;
    }

    public TreeNode getRoot() {
        return root;
    }
}
