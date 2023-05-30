package com.xiao.sweb.algorithm.violentSearch.dfs;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class DFS {
    List<Integer> list = new ArrayList<>();

    public int dfs(TreeNode root) {
        if (root != null) {
            if (root.left != null){
                dfs(root.left);
            }
            list.add(root.val);
            if (root.right != null){
                dfs(root.right);
            }
        }
        return 0;
    }
}
