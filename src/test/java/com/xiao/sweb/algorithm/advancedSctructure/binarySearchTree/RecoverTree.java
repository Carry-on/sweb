package com.xiao.sweb.algorithm.advancedSctructure.binarySearchTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 99. 恢复二叉搜索树
 */
public class RecoverTree {

    public void recoverTree(TreeNode root) {
        List<Integer> nums = new LinkedList<>();
        inorder(root, nums);
        int[] swaped = findTwoSwaped(nums);
        recover(root, 2, swaped[0], swaped[1]);
    }

    private void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0){
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }

    private int[] findTwoSwaped(List<Integer> nums) {
        int index1 = -1, index2 = -1;
        for (int i = 0; i < nums.size() - 1; i++) {
            if (nums.get(i + 1) < nums.get(i)) {
                index2 = i + 1;
                if (index1 == -1) {
                    index1 = i;
                } else {
                    break;
                }
            }
        }
        return new int[]{nums.get(index1), nums.get(index2)};
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }


}
