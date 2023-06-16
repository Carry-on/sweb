package com.xiao.sweb.algorithm.violentSearch.dfs;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DFS {
    List<Integer> list = new ArrayList<>();

    public int dfs(TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                dfs(root.left);
            }
            list.add(root.val);
            if (root.right != null) {
                dfs(root.right);
            }
        }
        return 0;
    }

    public List<Integer> dfsInorderByIteration(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.poll();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    public List<Integer> dfsPreorderByIteration(TreeNode root) {
        if (root == null) {
            return list;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.poll();
            root = root.right;
        }

        return list;
    }

    public List<Integer> dfsPostorderByIteration(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root.left);
                root = root.left;
            }
            root = stack.poll();
            if (root.right == null || root.right == prev) {
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.offer(root);
                root = root.right;
            }

        }
        return list;
    }
}
