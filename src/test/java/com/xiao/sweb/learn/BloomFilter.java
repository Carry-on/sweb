package com.xiao.sweb.learn;

import java.util.BitSet;

public class BloomFilter {
    private BitSet bitSet;
    private int size;
    private int[] seeds;

    public BloomFilter(int size, int[] seeds) {
        this.size = size;
        this.seeds = seeds;
        this.bitSet = new BitSet(size);
    }

    public void add(String value) {
        for (int seed : seeds) {
            int hash = getHash(value, seed);
            bitSet.set(hash, true);
        }
    }

    public boolean contains(String value) {
        for (int seed : seeds) {
            int hash = getHash(value, seed);
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }

    private int getHash(String value, int seed) {
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = seed * hash + value.charAt(i);
        }
        hash = hash % size;
        return Math.abs(hash);
    }

    public static void main(String[] args) {
        int size = 1000;
        int[] seeds = {3, 5, 7, 11, 13};

        BloomFilter bloomFilter = new BloomFilter(size, seeds);

        bloomFilter.add("apple");
        bloomFilter.add("banana");
        bloomFilter.add("orange");

        System.out.println(bloomFilter.contains("apple"));    // true
        System.out.println(bloomFilter.contains("banana"));   // true
        System.out.println(bloomFilter.contains("orange"));   // true
        System.out.println(bloomFilter.contains("grape"));    // false
        System.out.println(bloomFilter.contains("watermelon"));   // false
    }
}
