package com.dayone.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

public class TestScheduler {

    @Scheduled(cron = "0/5 * * * * *")
    public void test(){
        System.out.println("now -> " + System.currentTimeMillis());
    }

}
