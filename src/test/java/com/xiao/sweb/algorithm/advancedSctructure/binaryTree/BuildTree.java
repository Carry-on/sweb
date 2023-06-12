package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTree {

    Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode buildTreeByPreAndIn(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildByPreAndIn(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode buildByPreAndIn(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int preRoot = preStart;
        int inRoot = indexMap.get(preorder[preStart]);
        TreeNode root = new TreeNode(preorder[preRoot]);
        int sizeLeft = inRoot - inStart;
        root.left = buildByPreAndIn(preorder, inorder, preStart + 1, preStart + sizeLeft, inStart, inRoot - 1);
        root.right = buildByPreAndIn(preorder, inorder, preStart + sizeLeft + 1, preEnd, inRoot + 1, inEnd);
        return root;
    }

    public TreeNode buildTreeByInAndPost(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildByInAndPost(inorder, postorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode buildByInAndPost(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd) {
            return null;
        }
        int postRoot = postEnd;
        int inRoot = indexMap.get(postorder[postRoot]);
        int leftSize = inRoot - inStart;
        TreeNode root = new TreeNode(postorder[postRoot]);
        root.left = buildByInAndPost(inorder, postorder, inStart, inRoot - 1, postStart, postStart + leftSize -1);
        root.right = buildByInAndPost(inorder, postorder, inRoot + 1, inEnd, postStart + leftSize, postEnd - 1);
        return root;
    }
}
