package com.xiao.sweb.learn;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class test {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.right  = right;
        List<Integer> list = rightSideView(root);
        for (Integer i : list){
            System.out.println(i);
        }
    }


    public static List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        q.offer(root);
        if(!q.isEmpty()){
            int sz = q.size();
            for(int i=0; i<sz; i++){
                TreeNode cur = q.poll();
                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                }
                if(i == sz - 1){
                    res.add(cur.val);
                }
            }
        }
        return res;
    }
}
