package com.xiao.sweb.job;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Log4j2
public class ExecutorTask {
    public static final int coreThread = 1;
    // cpu核心数
    public static int maxThread = Runtime.getRuntime().availableProcessors();

    public static CompletableFuture asyExecutorTask(Supplier supplier){
        ExecutorService executorService = new ThreadPoolExecutor(coreThread, maxThread,
                0L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(supplier, executorService);
        future.thenAccept(e->{
            log.info("异步任务完成！~ ");
        });
        return future;
    }

}
