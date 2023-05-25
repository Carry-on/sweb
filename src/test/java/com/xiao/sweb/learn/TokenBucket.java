package com.xiao.sweb.learn;

import java.time.Instant;

public class TokenBucket {
    private final long capacity;
    private double tokens;
    private Instant lastRefillTime;
    private final double refillRate;

    public static void main(String[] args) {
        TokenBucket tokenBucket = new TokenBucket(5, 1.0); // 令牌桶容量为10，填充速率为1.0个/秒
        for (int i = 0; i < 20; i++) {
            if (tokenBucket.tryConsume()) {
                System.out.println("正常处理请求");
            } else {
                System.out.println("请求被限流");
            }
            try {
                Thread.sleep(500); // 每0.5秒尝试一次请求
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public TokenBucket(long capacity, double refillRate) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.lastRefillTime = Instant.now();
        this.refillRate = refillRate;
    }

    public synchronized boolean tryConsume() {
        refill();
        if (tokens >= 1.0) {
            tokens -= 1.0;
            return true;
        } else {
            return false;
        }
    }

    private void refill() {
        Instant now = Instant.now();
        double elapseTime = (now.toEpochMilli() - lastRefillTime.toEpochMilli()) / 1000.0;
        tokens = Math.min(tokens + elapseTime * refillRate, capacity);
        lastRefillTime = now;
    }
}
