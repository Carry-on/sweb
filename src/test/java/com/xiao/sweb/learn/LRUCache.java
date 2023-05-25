package com.xiao.sweb.learn;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int capacity;
    private Map<K, Node<K, V>> cache;
    private Node<K, V> head;
    private Node<K, V> tail;

    public static void main(String[] args) {
        LRUCache<Integer, Integer> cache = new LRUCache<>(2);
        cache.put(1, 1); // 添加键为"a"，值为1的元素
        cache.put(2, 2); // 添加键为"b"，值为2的元素
        System.out.println(cache.get(1)); // 输出1，访问键为"a"的元素，将其移动到链表头部
        cache.put(3, 3); // 添加键为"c"，值为3的元素，因为容量只有2，所以会删除最久未使用的元素"b"
        System.out.println(cache.get(2)); // 输出null，元素"b"已经被删除
        System.out.println(cache.get(1)); // 输出1，元素"a"已经在之前被访问过，将其移动到链表头部
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    public synchronized V get(int key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        moveToHead(node);
        return node.getValue();
    }

    public synchronized void put(K key, V value) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            node.setValue(value);
            moveToHead(node);
        } else {
            node = new Node<>(key, value);
            cache.put(key, node);
            addToHead(node);
            if (cache.size() > capacity) {
                removeTail();
            }
        }

    }

    private void moveToHead(Node<K, V> node) {
        if (head == node) {
            return;
        }
        if (tail == node) {
            tail = node.getPrev();
            tail.setNext(null);
        } else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
        node.setPrev(null);
        node.setNext(head);
        head.setPrev(node);
        head = node;
    }

    private void addToHead(Node<K, V> node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setPrev(null);
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
    }

    private void removeTail() {
        cache.remove(tail.getKey());
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail.getPrev().setNext(null);
            tail = tail.getPrev();
        }
    }

    class Node<K, V> {
        private final K key;
        private V value;
        private Node<K, V> prev;
        private Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
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

        public Node<K, V> getPrev() {
            return prev;
        }

        public void setPrev(Node<K, V> prev) {
            this.prev = prev;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }
}
