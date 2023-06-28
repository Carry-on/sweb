package com.xiao.sweb.algorithm.advancedSctructure.binaryTree;

import com.xiao.sweb.algorithm.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 37. 序列化二叉树
 */
public class SerializeTree {

    public String serialize(TreeNode root){
        return rSerialize(root, "");
    }

    public String rSerialize(TreeNode root, String str){
        if (root == null){
            str += "None,";
        }else {
            str += root.val + ",";
            str = rSerialize(root.left, str);
            str = rSerialize(root.right, str);
        }
        return str;
    }

    public TreeNode deserialize(String data){
        String[] dataArray = data.split(",");
        List<String> dataList = new ArrayList<>(Arrays.asList(dataArray));
        return rDeserialize(dataList);
    }

    public TreeNode rDeserialize(List<String> dataList){
        if (dataList.get(0).equals("None")){
            dataList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rDeserialize(dataList);
        root.right = rDeserialize(dataList);
        return root;
    }
}
