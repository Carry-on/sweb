package com.xiao.sweb.leetcode;

import java.util.Arrays;

/**
 * 2332. 坐上公交的最晚时间
 */
public class Solution2332LatestTimeCatchTheBus {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int pi = 0;
        int space = 0;
        for(int arrive : buses){
            space = capacity;
            while (space > 0 && pi < passengers.length && passengers[pi] <= arrive){
                space--;
                pi++;
            }
        }
        pi--;
        int res = space > 0 ? buses[buses.length-1] : passengers[pi];
        while (pi > 0 && passengers[pi] == res){
            pi--;
            res--;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution2332LatestTimeCatchTheBus bus = new Solution2332LatestTimeCatchTheBus();
//        int[] buse = new int[]{20, 30, 10};
//        int[] passengers = new int[]{19,13,26,4,25,11,21};
        int[] buse = new int[]{10,20};
        int[] passengers = new int[]{2,17,18,19};
        System.out.println(bus.latestTimeCatchTheBus(buse, passengers, 2));
    }
}
