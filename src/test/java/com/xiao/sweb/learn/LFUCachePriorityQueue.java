package com.xiao.sweb.learn;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCachePriorityQueue<K, V> {
    private int capacity;
    private Map<K, Node<K, V>> cache;
    private PriorityQueue<Node<K, V>> priorityQueue;

    public LFUCachePriorityQueue(int capacity){
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>();
    }

    public void put(K key, V value){
        if (capacity == 0){
            return;
        }
        Node<K, V> node = cache.get(key);
        if (node != null){
            node.value = value;
            updateFreq(node);
        }else{
            evictIfNeed();
            node = new Node<>(key, value);
            cache.put(key, node);
            priorityQueue.offer(node);
        }
    }

    public V get(K key){
        Node<K, V> node = cache.get(key);
        if (node != null){
            updateFreq(node);
            return node.value;
        }
        return null;
    }

    private void evictIfNeed() {
        if (cache.size() >= capacity){
            Node<K, V> node = priorityQueue.poll();
            cache.remove(node.key);
        }
    }

    private void updateFreq(Node<K,V> node) {
        priorityQueue.remove(node);
        node.freq++;
        node.accessTime = System.nanoTime();
        priorityQueue.offer(node);
    }

    static class Node<K, V> implements Comparable<Node<K, V>>{
        K key;
        V value;
        int freq;
//        long createTime;
        long accessTime;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.freq = 1;
//            this.createTime = System.nanoTime();
            this.accessTime = System.nanoTime();
        }

        @Override
        public int compareTo(Node<K, V> o) {
            int freqComparison = Integer.compare(this.freq, o.freq);
            return freqComparison != 0 ? freqComparison : Long.compare(this.accessTime, o.accessTime);
        }
    }

    public static void main(String[] args) {
        LFUCachePriorityQueue<Integer, String> cache = new LFUCachePriorityQueue<>(2);
        cache.put(1, "A");
        cache.put(2, "B");
        System.out.println(cache.get(1)); // Output: A
        cache.put(3, "C");
        System.out.println(cache.get(2)); // Output: null
        System.out.println(cache.get(3)); // Output: C
        cache.put(4, "D");
        System.out.println(cache.get(1)); // Output: null
        System.out.println(cache.get(3)); // Output: C
        System.out.println(cache.get(4)); // Output: D
    }
}
