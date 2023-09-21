package com.xiao.sweb.algorithm.arrayorstring;

import java.util.ArrayList;
import java.util.List;

/**
 * 约瑟夫环，又称“丢手绢问题“
 */
public class JosephusProblem {
    public int josephusProblem(int n, int m){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i+1);
        }

        int index = 0;
        while(list.size() > 1){
            index = (index + m -1) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }
}
