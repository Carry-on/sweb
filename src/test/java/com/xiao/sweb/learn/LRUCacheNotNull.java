package com.xiao.sweb.learn;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheNotNull {
    private int capacity;
    private Map<Integer, Node> cache;
    private Node head, tail;

    public LRUCacheNotNull(int capacity){
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        Node node = cache.get(key);
        if (node == null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value){
        Node node = cache.get(key);
        if (node == null){
            node = new Node(key, value);
            cache.put(key, node);
            addToHead(node);
            if (cache.size() > capacity){
                removeTail();
            }
        }else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeTail() {
        cache.remove(tail.prev.key);
        removeNode(tail.prev);
    }

    class Node{
        int key;
        int value;
        Node prev, next;

        public Node(){}

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
}
