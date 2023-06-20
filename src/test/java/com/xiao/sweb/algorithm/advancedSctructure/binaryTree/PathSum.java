package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 */
public class PathSum {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path  = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target){
        dfs(root, target);
        return res;
    }

    private void dfs(TreeNode root, int target){
        if (root == null){
            return;
        }
        path.offerLast(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0){
            res.add(new ArrayList<>(path));
        }
        dfs(root.left, target);
        dfs(root.right, target);
        path.pollLast();
    }
}
