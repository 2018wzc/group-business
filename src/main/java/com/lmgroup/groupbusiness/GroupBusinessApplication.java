package com.lmgroup.groupbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@ServletComponentScan("com.lmgroup.groupbusiness.schedule")
public class GroupBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupBusinessApplication.class, args);
    }


}
