package com.xiao.sweb.learn;

import java.util.HashMap;
import java.util.Map;

public class LFUMapCache {
    private Map<Integer, CacheElement> cache;
    private Map<Integer, Map<Integer, CacheElement>> freqMap;
    private int capacity;
    private int minFreq;

    public static void main(String[] args) {
        LFUMapCache cache = new LFUMapCache(2);
        cache.put(1, 1); // 添加键为"a"，值为1的元素
        cache.put(2, 2); // 添加键为"b"，值为2的元素
        System.out.println(cache.get(1)); // 输出1，访问键为"a"的元素，访问次数加1
        cache.put(3, 3); // 添加键为"c"，值为3的元素，因为容量只有2，所以会删除访问次数最少的元素"b"
        System.out.println(cache.get(2)); // 输出null，元素"b"已经被删除
        System.out.println(cache.get(1)); // 输出1，元素"a"的访问次数加1，并且时间戳更新
    }

    public LFUMapCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 1;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        CacheElement element = cache.get(key);
        int oldFreq = element.freq;
        freqMap.get(oldFreq).remove(key);

        if (freqMap.get(oldFreq).isEmpty()) {
            freqMap.remove(oldFreq);
            if (oldFreq == this.minFreq) {
                this.minFreq++;
            }
        }
        element.freq++;
        int newFreq = element.freq;
        if (!freqMap.containsKey(newFreq)) {
            freqMap.put(newFreq, new HashMap<>());
        }
        freqMap.get(newFreq).put(key, element);
        return element.value;
    }

    public void put(int key, int value) {
        if (this.capacity <= 0) {
            return;
        }
        if (cache.containsKey(key)) {
            CacheElement element = cache.get(key);
            element.value = value;
            get(key);
            return;
        }
        CacheElement newElement = new CacheElement(key, value, 1);
        if (cache.size() == capacity){
            evict();
        }
        cache.put(key, newElement);
        if (!freqMap.containsKey(newElement.freq)){
            freqMap.put(newElement.freq, new HashMap<>());
        }
        freqMap.get(newElement.freq).put(key, newElement);
        this.minFreq = 1;
    }

    private void evict(){
        Map<Integer, CacheElement> minFreqMap = freqMap.get(minFreq);
        int evictKey = minFreqMap.entrySet().iterator().next().getKey();
        minFreqMap.remove(evictKey);
        if (minFreqMap.isEmpty()){
            freqMap.remove(minFreq);
        }
        cache.remove(evictKey);
    }

    class CacheElement {
        int key;
        int value;
        int freq;

        public CacheElement(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }
}
