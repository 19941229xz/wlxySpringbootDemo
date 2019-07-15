package org.wlxy.example;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wlxy.example.common.HttpCode;
import org.wlxy.example.common.MyException;
import org.wlxy.example.config.SystemConfig;

@Controller
@SpringBootApplication
@Slf4j
@MapperScan({"org.wlxy.example.dao","org.wlxy.example.common.task"})
@EnableCaching
//@EnableScheduling
public class ExampleApplication {

    @Value("${spring.profiles.active}")
    private String appInfo;

    @Autowired
    private Environment environment;

    @Autowired
    SystemConfig systemConfig;

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {



//            throw new MyException(HttpCode.ERROR);


        return systemConfig.toString()+"";
    }


}
