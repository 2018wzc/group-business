package com.lmgroup.groupbusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class GroupBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupBusinessApplication.class, args);
    }


}
