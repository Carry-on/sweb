package com.xiao.sweb.learn;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LFUSetCache {
    private int capacity;
    private int time;
    private Map<Integer, CacheNode> cache;
    private TreeSet<CacheNode> set;

    public LFUSetCache(int capacity){
        this.capacity = capacity;
        this.time = 0;
        this.cache = new HashMap<>();
    }

    public int get(int key){
        if (capacity == 0){
            return -1;
        }
        if (!cache.containsKey(key)){
            return -1;
        }
        CacheNode node = cache.get(key);
        set.remove(node);
        node.cnt++;
        node.time++;
        set.add(node);
        cache.put(key, node);
        return node.value;
    }

    public void put(int key, int value){
        if (capacity == 0){
            return;
        }
        if (!cache.containsKey(key)){

        }
    }


    class CacheNode implements Comparable<CacheNode>{
        int key;
        int value;
        int cnt;
        int time;

        public CacheNode(int key, int value, int cnt, int time){
            this.key = key;
            this.value = value;
            this.cnt = cnt;
            this.time = time;
        }

        @Override
        public int compareTo(CacheNode o) {
            return cnt==o.cnt ? time - o.time : cnt - o.cnt;
        }

        public boolean equals(Object obj){
            if (this == obj){
                return true;
            }
            if (obj instanceof CacheNode){
                CacheNode rhs = (CacheNode) obj;
                return this.cnt==rhs.cnt && this.time==rhs.time;
            }
            return false;
        }

        public int hashCode(){
            return cnt * 1000000007 + time;
        }
    }
}
