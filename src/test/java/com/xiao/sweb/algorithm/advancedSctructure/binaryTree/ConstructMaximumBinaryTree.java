package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 654. 最大二叉树
 */
public class ConstructMaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
//        return build(nums, 0, nums.length);
        int n = nums.length;
        List<Integer> stack = new ArrayList<>();
        TreeNode[] tree = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > nums[stack.get(stack.size() - 1)]) {
                tree[i].left = tree[stack.get(stack.size() - 1)];
                stack.remove(stack.size() - 1);
            }
            if (!stack.isEmpty()) {
                tree[stack.get(stack.size() - 1)].right = tree[i];
            }
            stack.add(i);
        }
        return tree[stack.get(0)];

    }

    private TreeNode build(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int best = left;
        for (int i = left + 1; i < right; i++) {
            if (nums[i] > nums[best]) {
                best = i;
            }
        }
        TreeNode root = new TreeNode(nums[best]);
        root.left = build(nums, left, best - 1);
        root.right = build(nums, best + 1, right);
        return root;
    }
}
