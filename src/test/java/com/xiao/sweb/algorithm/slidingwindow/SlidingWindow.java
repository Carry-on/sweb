package com.xiao.sweb.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口算法框架
 */
public class SlidingWindow {

    public void slidingWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新

            // 判断左侧窗口是否要收缩
            while (window.containsKey('a')) {
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left--;
                // 进行窗口内数据的一系列更新
            }
        }
    }
}
