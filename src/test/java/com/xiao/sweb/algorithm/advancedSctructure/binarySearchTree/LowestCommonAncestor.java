package com.xiao.sweb.algorithm.advancedSctructure.binarySearchTree;

import com.xiao.sweb.algorithm.TreeNode;

/**
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 */
public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        TreeNode ancestor = root;
//        while (true) {
//            if (p.val < ancestor.val && q.val < ancestor.val) {
//                ancestor = ancestor.left;
//            } else if (p.val > ancestor.val && q.val > ancestor.val) {
//                ancestor = ancestor.right;
//            } else {
//                break;
//            }
//        }
//        return ancestor;
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        } else if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }
}
