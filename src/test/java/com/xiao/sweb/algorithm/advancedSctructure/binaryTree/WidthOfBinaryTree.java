package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 662. 二叉树最大宽度
 */
public class WidthOfBinaryTree {

    Map<Integer, Integer> levelMin = new HashMap<>();

    public int widthOfBinaryTree(TreeNode root) {
//        return dfs(root, 1, 1);
        int res = 1;
        List<Pair<TreeNode, Integer>> arr = new ArrayList<Pair<TreeNode, Integer>>();
        arr.add(new ImmutablePair<TreeNode, Integer>(root, 1));
        while (!arr.isEmpty()) {
            List<Pair<TreeNode, Integer>> tmp = new ArrayList<>();
            for (Pair<TreeNode, Integer> pair : tmp) {
                TreeNode node = pair.getLeft();
                int index = pair.getRight();
                if (node.left != null){
                    tmp.add(new ImmutablePair<>(node.left, index * 2));
                }
                if (node.right != null){
                    tmp.add(new ImmutablePair<>(node.right, index * 2 + 1));
                }
            }
            res = Math.max(res, arr.get(arr.size() - 1).getValue() - arr.get(0).getValue() + 1);
            arr = tmp;
        }
        return res;
    }

    private int dfs(TreeNode node, int depth, int index) {
        if (node == null) {
            return 0;
        }
        levelMin.putIfAbsent(depth, index);
        return Math.max(index - levelMin.get(depth) + 1, Math.max(dfs(node.left, depth + 1, index * 2), dfs(node.right, depth + 1, index * 2 + 1)));
    }
}
