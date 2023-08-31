package com.xiao.sweb.algorithm.arrayorstring;

import java.util.Arrays;

/**
 * 274. H 指数
 */
public class HIndex {
    public int hIndex(int[] citations) {
        int h = 0, i = citations.length - 1;
        Arrays.sort(citations);
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

}
