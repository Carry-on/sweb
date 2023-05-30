package com.xiao.sweb.algorithm.violentSearch.bfs;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最小深度
 */
public class MinDepth {
    public int minDepth(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                if (cur.left == null && cur.right == null){
                    return depth;
                }
                if (cur.left != null){
                    q.offer(cur.left);
                }
                if (cur.right !=null){
                    q.offer(cur.right);
                }
            }
            depth++;
        }

        return depth;
    }
}
