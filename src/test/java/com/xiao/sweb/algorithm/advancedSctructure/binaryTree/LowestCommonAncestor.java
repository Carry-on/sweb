package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 */
public class LowestCommonAncestor {

    // 存储父节点
    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        dfs(root);
        while (p != null){
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null){
            if (visited.contains(q.val)){
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root){
        if (root.left != null){
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null){
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
