package com.xiao.sweb.algorithm.dynamicProgramming.twoDimensionalDP;

import java.util.Scanner;

/**
 * HJ16 购物单
 */
public class ShoppingList {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int m = sc.nextInt();

        Goods[] goods = new Goods[m];
        for(int i = 0; i < m; i++){
            goods[i] = new Goods();
        }
        for(int i = 0; i < m; i++){
            int v = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            goods[i].v = v;
            goods[i].p = p * v;  // 直接用p*v，方便后面计算
            if(q==0){
                goods[i].main = true;
            }else if(goods[q-1].a1 == -1){
                goods[q-1].a1 = i;
            }else{
                goods[q-1].a2 = i;
            }
        }

        int[] dp = new int[N+1];
        for(int i = 1; i <= m; i++){
            for(int j = N; j >= 0; j--){
                if(!goods[i-1].main){
                    continue;
                }
                if(j>=goods[i-1].v){
                    dp[j] = Math.max(dp[j], dp[j-goods[i-1].v] + goods[i-1].p);
                }
                if(goods[i-1].a1 != -1 && j >= goods[i-1].v + goods[goods[i-1].a1].v){
                    dp[j] = Math.max(dp[j], dp[j-goods[i-1].v - goods[goods[i-1].a1].v] + goods[i-1].p + goods[goods[i-1].a1].p);
                }
                if(goods[i-1].a2 != -1 && j >= goods[i-1].v + goods[goods[i-1].a2].v){
                    dp[j] = Math.max(dp[j], dp[j-goods[i-1].v - goods[goods[i-1].a2].v] + goods[i-1].p + goods[goods[i-1].a2].p);
                }
                if(goods[i-1].a1 != -1 && goods[i-1].a2 != -1 &&  j >= goods[i-1].v + goods[goods[i-1].a1].v + goods[goods[i-1].a2].v){
                    dp[j] = Math.max(dp[j], dp[j-goods[i-1].v - goods[goods[i-1].a1].v - goods[goods[i-1].a2].v] + goods[i-1].p + goods[goods[i-1].a1].p + goods[goods[i-1].a2].p);
                }
            }
        }
        System.out.println(dp[N]);
    }
}

class Goods {
    int v;
    int p;
    boolean main = false;

    int a1 = -1;  //定义附件1的编号
    int a2 = -1;  //定义附件2的编号
}
