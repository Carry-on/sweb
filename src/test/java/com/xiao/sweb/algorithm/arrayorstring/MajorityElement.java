package com.xiao.sweb.algorithm.arrayorstring;

import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 */
public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put(x, !map.containsKey(x) ? 1 : map.get(x) + 1);
        }
        Map.Entry<Integer, Integer> mEntry = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (mEntry == null || entry.getValue() > mEntry.getValue()) {
                mEntry = entry;
            }
        }
        return mEntry.getKey();
    }
}
