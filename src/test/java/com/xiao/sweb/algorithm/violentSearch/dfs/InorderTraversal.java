package com.xiao.sweb.algorithm.violentSearch.dfs;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 中序遍历
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public List<Integer> inorderTraversalIteration(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        while (root != null || !q.isEmpty()) {
            while (root != null) {
                q.offer(root);
                root = root.left;
            }
            root = q.poll();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

}
