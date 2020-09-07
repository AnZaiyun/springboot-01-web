package com.anzaiyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.anzaiyun.mapper")
public class Springboot01WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01WebApplication.class, args);
    }

}
