package com.xiao.sweb.controller;

import com.xiao.sweb.tasks.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class TaskController {

    @Autowired
    AsyncTask asyncTask;

    @RequestMapping("asyncTask")
    public String testAsyncTask() throws Exception {
        long start = System.currentTimeMillis();

        Future<Boolean> a = asyncTask.doTask1();
        Future<Boolean> b = asyncTask.doTask2();
        Future<Boolean> c = asyncTask.doTask3();

        while (!a.isDone() || !b.isDone() || !c.isDone()) {
            if (a.isDone() && b.isDone() && c.isDone()) {
                break;
            }
        }

        long end = System.currentTimeMillis();
        String times = "All tasks need time: " + (end - start) + "millis";
        return times;
    }
}
