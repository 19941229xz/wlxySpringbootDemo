package org.wlxy.example.common.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.wlxy.example.dao.UserDao;

@Component
@Configuration
//@EnableScheduling
@Slf4j
public class TestTask {


//    @Scheduled(cron = "0/5 * * * * ?")
    @Scheduled(fixedRate = 5000)
    public void Task1(){
        log.info("我是task1");
    }





}
