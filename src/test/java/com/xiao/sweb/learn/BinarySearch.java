package com.xiao.sweb.learn;

public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                return mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};
        int target = 7;

        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("目标元素 " + target + " 在索引 " + index + " 处找到");
        } else {
            System.out.println("目标元素 " + target + " 不存在");
        }
    }
}
