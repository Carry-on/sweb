package com.xiao.sweb.algorithm.advancedSctructure.binarySearchTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98. 验证二叉搜索树
 */
public class IsValidBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right =  new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(isValidBST(root));
    }

    public static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
//        return dfs(root, null, null);
        int prev = Integer.MIN_VALUE;
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev >= root.val){
                return false;
            }
            prev = root.val;
            root = root.right;
        }
        return true;
    }

    private static boolean dfs(TreeNode root, Integer min, Integer max) {
        if (root == null){
            return true;
        }
        if (min != null && root.val <= min){
            return false;
        }
        if (max != null && root.val >= max){
            return false;
        }
        return dfs(root.left, min, root.val) && dfs(root.right, root.val, max);
    }
}
