package com.xiao.sweb.algorithm.baseStructure.listDoublePointer;

/**
 * 392. 判断子序列
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t){
        int sLen = s.length(), tLen = t.length();
        int sIdx = 0, tIdx = 0;
        while (sIdx < sLen && tIdx < tLen){
            if (s.charAt(sIdx) == t.charAt(tIdx)){
                sIdx++;
            }
            tIdx++;
        }
        return sIdx == sLen;
    }
}
