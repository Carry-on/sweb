package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

/**
 * 563. 二叉树的坡度
 */
public class FindTilt {
    int ans;
    public int findTilt(TreeNode root){
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root){
        if (root == null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        ans += Math.abs(left - right);
        return left + right + root.val;
    }
}
