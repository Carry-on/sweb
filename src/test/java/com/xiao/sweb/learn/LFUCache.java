package com.xiao.sweb.learn;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final PriorityQueue<Node<K, V>> minHeap;

    public static void main(String[] args) {
        LFUCache<String, Integer> cache = new LFUCache<>(2);
        cache.put("a", 1); // 添加键为"a"，值为1的元素
        cache.put("b", 2); // 添加键为"b"，值为2的元素
        System.out.println(cache.get("a")); // 输出1，访问键为"a"的元素，访问次数加1
        cache.put("c", 3); // 添加键为"c"，值为3的元素，因为容量只有2，所以会删除访问次数最少的元素"b"
        System.out.println(cache.get("b")); // 输出null，元素"b"已经被删除
        System.out.println(cache.get("a")); // 输出1，元素"a"的访问次数加1，并且时间戳更新
    }

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        // 按照访问次数排序的小根堆
        this.minHeap = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.getFrequency()).thenComparingLong(Node::getTime));
    }

    public synchronized V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        node.setFrequency(node.getFrequency() + 1);
        node.setTime(System.nanoTime());
        minHeap.remove(node);
        minHeap.offer(node);
        return node.getValue();
    }

    public synchronized void put(K key, V value) {
        if (capacity <= 0) {
            return;
        }
        Node<K, V> node = cache.get(key);
        if (node != null) {
            node.setValue(value);
            node.setFrequency(node.getFrequency() + 1);
            node.setTime(System.nanoTime());
            minHeap.remove(node);
            minHeap.offer(node);
            return;
        }
        if (cache.size() > capacity) {
            Node<K, V> minNode = minHeap.poll();
            if (minNode != null){
                cache.remove(minNode);
            }
        }
        node = new Node<>(key, value, 1, System.nanoTime());
        cache.put(key, node);
        minHeap.offer(node);
    }

    class Node<K, V> {
        private final K key;
        private V value;
        private int frequency;
        private long time;

        Node(K key, V value, int frequency, long time) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
            this.time = time;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}
