package com.xiao.sweb.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c 是将移⼊窗⼝的字符
            char c = s.charAt(right);
            // 右移窗⼝
            right++;
            // 进⾏窗⼝内数据的⼀系列更新
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // 判断左侧窗⼝是否要收缩
            while (valid == need.size()) {
                // 在这⾥更新最⼩覆盖⼦串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗⼝的字符
                char d = s.charAt(left);
                // 左移窗⼝
                left++;
                // 进⾏窗⼝内数据的⼀系列更新
                if (need.containsKey(d)) {
                    window.put(d, window.get(d) - 1);
                    if (window.get(d) < Integer.valueOf(need.get(d))) {
                        valid--;
                    }

                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
