package com.xiao.sweb.algorithm.hj;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * HJ20 密码验证合格程序
 *
 * 密码要求:
 * 1.长度超过8位
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
 */
public class PasswordMatch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
            while (in.hasNext()) { // 注意 while 处理多个 case
            String pw = in.nextLine();
            if(pw.length() <= 8){
                System.out.println("length NG");
                continue;
            }
            if(!getMatch(pw)){
                System.out.println("match NG");
                continue;
            }
            if(getString(pw, 0, 3)){
                System.out.println("string NG");
                continue;
            }
            System.out.println("OK");
        }
    }

    // 校验是否有重复子串
    public static boolean getString(String str, int l, int r){
        if(r >= str.length()){
            return false;
        }
        if(str.substring(r).contains(str.substring(l, r))){
            return true;
        } else {
            return getString(str, l+1, r+1);
        }
    }

    // 检查是否满足正则
    public static boolean getMatch(String str){
        int count = 0;
        Pattern p1 = Pattern.compile("[A-Z]");
        if(p1.matcher(str).find()){
            count++;
        }
        Pattern p2 = Pattern.compile("[a-z]");
        if(p2.matcher(str).find()){
            count++;
        }
        Pattern p3 = Pattern.compile("[0-9]");
        if(p3.matcher(str).find()){
            count++;
        }
        Pattern p4 = Pattern.compile("[^a-zA-Z0-9]");
        if(p4.matcher(str).find()){
            count++;
        }
        if(count >= 3){
            return true;
        }else{
            return false;
        }
    }
}
