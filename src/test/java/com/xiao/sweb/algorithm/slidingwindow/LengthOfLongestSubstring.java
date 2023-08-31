package com.xiao.sweb.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "pwwkew";
        LengthOfLongestSubstring obj = new LengthOfLongestSubstring();
        int res = obj.lengthOfLongestSubstring(s);
        System.out.println(res);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0;
        int res = 0;
        while (end < n) {
            char cur = s.charAt(end);
            if (map.containsKey(cur)) {
                start = Math.max(start, map.get(cur) + 1);
            }
            map.put(cur, end);
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}
