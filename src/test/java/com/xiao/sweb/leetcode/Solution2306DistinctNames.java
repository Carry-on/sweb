package com.xiao.sweb.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class Solution2306DistinctNames {
    public long distinctNames(String[] ideas) {
        long res = 0;
        Map<Character, Set<String>> names = new HashMap<>();
        for (String idea : ideas){
            names.put(idea.charAt(0), new HashSet<>());
            names.get(idea.charAt(0)).add(idea.substring(1));
        }
        for (Map.Entry<Character, Set<String>> entryA : names.entrySet()){
            char preA = entryA.getKey();
            Set<String> setA = entryA.getValue();
            for (Map.Entry<Character, Set<String>> entryB : names.entrySet()){
                char preB = entryB.getKey();
                Set<String> setB = entryB.getValue();
                if (preB == preA){
                    continue;
                }
                int intersect = getIntersectSize(setA, setB);
                res += (long) (setA.size() - intersect) * (setB.size() - intersect);
            }
        }
        return res;
    }

    public int getIntersectSize(Set<String> a, Set<String> b){
        int ans = 0;
        for (String s : a){
            if (b.contains(s)){
                ans++;
            }
        }
        return ans;
    }
}
