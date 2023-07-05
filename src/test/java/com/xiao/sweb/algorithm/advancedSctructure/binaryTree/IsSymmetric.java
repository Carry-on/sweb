package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

/**
 * 101. 对称二叉树
 */
public class IsSymmetric {

    public boolean isSymmetric(TreeNode root){
        return dfs(root, root);
    }

    private boolean dfs(TreeNode p, TreeNode q){
        if (p == null && q == null){
            return true;
        }
        if (p == null || q == null){
            return false;
        }
        return p.val == q.val && dfs(p.left, q.right) && dfs(p.right, q.left);
    }

}
