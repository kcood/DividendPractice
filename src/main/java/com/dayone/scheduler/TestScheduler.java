package com.dayone.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

public class TestScheduler {

    @Scheduled(cron = "0/5 * * * * *")
    public void test(){
        System.out.println("now -> " + Thread.currentThread().getName() + ": " + System.currentTimeMillis());
    }

    @Scheduled(cron = " ")
    public void yahooFinanceScheduling(){
        //저장된 회사 목록 조회


        //회사 하나씩 순회하면서 배당금 정보를 스크래핑


        //스크래핑한 배당금 정보 중 db에 없는 값은 저장
    }

}
