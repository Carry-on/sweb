package com.xiao.sweb.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestTask {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    //    @Scheduled(fixedRate = 1000)
//    @Scheduled(cron = "4-40 * * * * ? ") // http://cron.qqe2.com
    public void reportCurrentTime() {
        System.out.println("current time: " + DATE_FORMAT.format(new Date()));
    }
}
