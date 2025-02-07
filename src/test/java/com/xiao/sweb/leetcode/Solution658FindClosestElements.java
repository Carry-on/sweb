package com.xiao.sweb.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 658. 找到 K 个最接近的元素
 */
public class Solution658FindClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
//        List<Integer> list = new ArrayList<>();
//        for (int num : arr){
//            list.add(num);
//        }
//        Collections.sort(list, (a,b) ->{
//            if (Math.abs(x - a) != Math.abs(x-b)){
//                return Math.abs(x - a) - Math.abs(x-b);
//            } else {
//                return a - b;
//            }
//        });
//        List<Integer> ans = list.subList(0, k);
//        Collections.sort(ans);
//        return ans;


        int right = binarySearch(arr, x);


        List<Integer> ans = new ArrayList<>();
        return ans;
    }

    private int binarySearch(int[] arr, int x){
        int low = 0, high = arr.length - 1;
        while (low < high){
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x){
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
