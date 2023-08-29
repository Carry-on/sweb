package com.xiao.sweb.algorithm.baseStructure.listDoublePointer;

/**
 * 125. 验证回文串
 */
public class IsPalindrome {
//    public boolean isPalindrome(String s){
//        int n = s.length();
//        StringBuffer sGood = new StringBuffer();
//        for (int i = 0; i < n; i++) {
//            char c = s.charAt(i);
//            if (Character.isLetterOrDigit(c)){
//                sGood.append(Character.toLowerCase(c));
//            }
//        }
//        StringBuffer sGoodRev = new StringBuffer(sGood).reverse();
//        return sGoodRev.toString().equals(sGood.toString());
//    }

    public boolean isPalindrome(String s){
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right){
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))){
                right++;
            }
            if (left < right){
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))){
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
