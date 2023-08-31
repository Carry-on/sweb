package com.xiao.sweb.algorithm.arrayorstring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> map;
    Random random;
    public RandomizedSet(){
        nums = new ArrayList<>();
        map  = new HashMap<>();
        random = new Random();
    }

    public boolean inset(int val){
        if (map.containsKey(val)){
            return false;
        }
        int index = nums.size();
        nums.add(val);
        map.put(val, index);
        return true;
    }

    public boolean remove(int val){
        if (!map.containsKey(val)){
            return false;
        }
        int index = map.get(val);
        int last = nums.get(nums.size() - 1);
        nums.set(index, last);
        map.put(last, index);
        nums.remove(nums.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom(){
        int index = random.nextInt(nums.size());
        return map.get(index);
    }
}
