package com.xiao.sweb.algorithm.advancedSctructure.binarySearchTree;

import java.util.Stack;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 */
public class VerifyPostorder {
    public boolean verifyPostorder(int[] postorder){
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--){
            if (postorder[i] > root) {
                return false;
            }
            while (!stack.isEmpty() && stack.peek() > postorder[i]){
                root = stack.pop();
            }
            stack.add(postorder[i]);
        }
        return true;
    }

    public boolean verifyPreorder(int[] preorder){
        Stack<Integer> stack = new Stack<>();
        int root = preorder[0];
        for (int i=1; i<preorder.length; i++){
            if (preorder[i] > root){
                return false;
            }
            while (!stack.isEmpty() && stack.peek() > preorder[i]){
                root = stack.pop();
            }
            stack.add(preorder[i]);
        }
        return true;
    }
}
