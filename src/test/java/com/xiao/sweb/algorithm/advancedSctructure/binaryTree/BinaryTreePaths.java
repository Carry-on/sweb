package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        constructPath(root, "", res);
        return res;
    }

    private void constructPath(TreeNode root, String path, List<String> res) {
        if (root != null) {
            StringBuilder sb = new StringBuilder(path);
            sb.append(root.val);
            if (root.left == null && root.right == null){
                res.add(sb.toString());
            }else {
                sb.append("->");
                constructPath(root.left, sb.toString(), res);
                constructPath(root.right, sb.toString(), res);
            }
        }
    }
}
