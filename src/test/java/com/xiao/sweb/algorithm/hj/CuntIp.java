package com.xiao.sweb.algorithm.hj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * HJ18 识别有效的IP地址和掩码并进行分类统计
 *
 * 请解析IP地址和对应的掩码，进行分类识别。要求按照A/B/C/D/E类地址归类，不合法的地址和掩码单独归类。
 * 所有的IP地址划分为 A,B,C,D,E五类
 * A类地址从1.0.0.0到126.255.255.255;
 * B类地址从128.0.0.0到191.255.255.255;
 * C类地址从192.0.0.0到223.255.255.255;
 * D类地址从224.0.0.0到239.255.255.255；
 * E类地址从240.0.0.0到255.255.255.255
 *
 *
 * 私网IP范围是：
 * 从10.0.0.0到10.255.255.255
 * 从172.16.0.0到172.31.255.255
 * 从192.168.0.0到192.168.255.255
 *
 * 子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
 * （注意二进制下全是1或者全是0均为非法子网掩码）
 * 注意：
 * 1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时请忽略
 * 2. 私有IP地址和A,B,C,D,E类地址是不冲突的
 */
public class CuntIp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] count = new int[7];
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            String str = in.nextLine();
            String[] arr = str.split("~");
            String ip = arr[0];
            String mask = arr[1];
            String[] ips = ip.split("\\.");
            int num1 = 0;
            int num2 = 0;
            try{
                num1 = Integer.parseInt(ips[0]);
                num2 = Integer.parseInt(ips[1]);
                Integer.parseInt(ips[2]);
                Integer.parseInt(ips[3]);
            }catch(Exception e){
                count[5]++;
                continue;
            }
            boolean isIp = isIp(ip);
            boolean isMask = isMask(mask);
            //如果IP和子网掩码都合法 就判断是哪种类型的IP地址
            if(isIp && isMask){
                count = countIp(ip, count);
                System.out.println("----" + count[5]);
            }else if((!isIp || !isMask) && num1 != 127 && num1 != 0){
                count[5]++;
//                System.out.println("====" + count[5]);
            }
        }
        for(int i=0; i<6; i++){
            System.out.print(count[i] + " ");
        }
        System.out.print(count[6]);
    }

    public static boolean isIp(String ip) {
        //1. 类似于【0.*.*.*】和【127.*.*.*】的IP地址不属于上述输入的任意一类，也不属于不合法ip地址，计数时可以忽略
        String[] ipArr = ip.split("\\.");
        Boolean isTrue = true;
        for (int i = 0; i < ipArr.length; i++) {
            if (ipArr[i].length() <= 0 || ipArr[i] == "") {
                isTrue = false;
            }
        }
        return isTrue;
    }

    public static boolean isMask(String mask) {
        boolean result = false;
        String[] maskArr = mask.split("\\.");
        //0000 0000~ 1111 1110 八位
        //子网掩码为二进制下前面是连续的1，然后全是0。（例如：255.255.255.32就是一个非法的掩码）
        // 注意二进制下全是1或者全是0均为非法
        int[] maskRange = {254, 252, 248, 240, 224, 192, 128, 0};
        List<Integer> list = new ArrayList<>();
        for (int i : maskRange) {
            list.add(i);
        }
        //255.255.255.
        if ("255".equals(maskArr[0]) && "255".equals(maskArr[1]) &&
                "255".equals(maskArr[2])) {
            if (list.contains(Integer.parseInt(maskArr[3]))) {
                result = true;
            }
        }
        //255.255.
        else if ("255".equals(maskArr[0]) && "255".equals(maskArr[1])) {
            if (list.contains(Integer.parseInt(maskArr[2])) && "0".equals(maskArr[3])) {
                result = true;
            }
        }
        //255.
        else if ("255".equals(maskArr[0])) {
            if (list.contains(Integer.parseInt(maskArr[1])) && "0".equals(maskArr[2]) &&
                    "0".equals(maskArr[3])) {
                result = true;
            }
        } else if (list.contains((Integer.parseInt(maskArr[0])))) {
            if ("0".equals(maskArr[1]) && "0".equals(maskArr[2]) &&
                    "0".equals(maskArr[3])) {
                result = true;
            }
        }
        return result;
    }

    //私有IP判断
    /*10.0.0.0-10.255.255.255  172.16.0.0-172.31.255.255  192.168.0.0-192.168.255.255*/
    public static boolean isPrivateIp(String ip) {
        String[] ipArr = ip.split("\\.");
        boolean result = false;
        //根据范围即可
        if (Integer.parseInt(ipArr[0]) == 10) {
            result = true;
        } else if (Integer.parseInt(ipArr[0]) == 172 &&
                (Integer.parseInt(ipArr[1]) > 15 && Integer.parseInt(ipArr[1]) < 32)) {
            result = true;
        } else if (Integer.parseInt(ipArr[0]) == 192 &&
                Integer.parseInt(ipArr[1]) == 168) {
            result = true;
        }
        return result;
    }

    public static int[] countIp(String ip, int[] count) {
        String[] arr = ip.split("\\.");
        int first = Integer.parseInt(arr[0]);
        //如果是私有ip
        if (isPrivateIp(ip)) {
            count[6] ++;
        }
        //判断其他地址范围
        /*        A类地址1.0.0.0~126.255.255.255;
                B类地址128.0.0.0~191.255.255.255;
                C类地址192.0.0.0~223.255.255.255;
                D类地址224.0.0.0~239.255.255.255；
                E类地址240.0.0.0~255.255.255.255*/
        if(first >= 1 && first <= 126){
            count[0]++;
        }else if(first >= 128 && first <= 191){
            count[1]++;
        }else if(first >= 192 && first <= 223){
            count[2]++;
        }else if(first >= 224 && first <= 239){
            count[3]++;
        }else if(first >= 240 && first <= 255){
            count[4]++;
        }

        return count;
    }
}
