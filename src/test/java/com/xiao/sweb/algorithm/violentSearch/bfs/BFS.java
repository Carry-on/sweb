package com.xiao.sweb.algorithm.violentSearch.bfs;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    // 计算从起点 start 到终点 target 的最近距离
    int BFS(TreeNode start, TreeNode target) {
        // 核心数据结构
        Queue<TreeNode> q = new LinkedList<>();
        // 避免走回头路
        Set<TreeNode> visited = new HashSet<>();

        // 将起点加入队列
        q.offer(start);
        visited.add(start);
        // 记录扩散的步数
        int step = 0;

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                if (cur.equals(target)) {
                    return step;
                }
                for (TreeNode x : cur.adj()) {
                    if (!visited.contains(x)){
                        q.offer(x);
                        visited.add(x);
                    }
                }
            }
            step++;
        }
        return step;
    }
}
