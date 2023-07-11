package com.xiao.sweb.algorithm.advancedSctructure.binarySearchTree;

import com.xiao.sweb.algorithm.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 */
public class ConvertBST {
    int sum = 0;

    public TreeNode convertBST(TreeNode root){
        if(root != null){
            convertBST(root.right);
            sum += root.val;
            root.val = sum;
            convertBST(root.left);
        }
        return root;
    }
}
