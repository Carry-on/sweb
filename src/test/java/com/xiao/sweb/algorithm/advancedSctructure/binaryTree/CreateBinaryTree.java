package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2196. 根据描述创建二叉树
 */
public class CreateBinaryTree {

    public TreeNode createBinaryTree(int[][] descriptions){
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int[] arr : descriptions){
            int parent = arr[0];
            int child = arr[1];
            int relation =  arr[2];
            if (!map.containsKey(parent)){
                map.put(parent, new TreeNode(parent));
            }
            if (!map.containsKey(child)){
                map.put(child, new TreeNode(child));
            }
            if (relation == 1){
                map.get(parent).left = map.get(child);
            }else{
                map.get(parent).right = map.get(child);
            }
            set.add(child);
        }
        for (int key : map.keySet()){
            if (!set.contains(key)){
                return map.get(key);
            }
        }
        return null;
    }
}
