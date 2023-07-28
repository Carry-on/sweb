package com.xiao.sweb.algorithm.advancedSctructure.binarySearchTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1932. 合并多棵二叉搜索树
 */
public class CanMerge {

    public Set<Integer> leaves = new HashSet<>();
    public Map<Integer, TreeNode> candidates = new HashMap<>();
    int prev = 0;

    public TreeNode canMerge(List<TreeNode> trees) {
        for (TreeNode tree : trees) {
            if (tree.left != null) {
                leaves.add(tree.left.val);
            }
            if (tree.right != null) {
                leaves.add(tree.right.val);
            }
            candidates.put(tree.val, tree);
        }

        for (TreeNode tree : trees) {
            if (leaves.contains(tree.val)) {
                candidates.remove(tree.val);
                prev = 0;
                return (dfs(tree) && candidates.isEmpty()) ? tree : null;
            }
        }
        return null;
    }

    private boolean dfs(TreeNode tree) {
        if (tree == null) {
            return true;
        }
        if (tree.left == null && tree.right == null && candidates.containsKey(tree.val)) {
            tree.left = candidates.get(tree.val).left;
            tree.right = candidates.get(tree.val).right;
            candidates.remove(tree.val);
        }
        if (!dfs(tree.left)) {
            return false;
        }
        if (tree.val <= prev) {
            return false;
        }
        prev = tree.val;
        return dfs(tree.right);
    }
}
