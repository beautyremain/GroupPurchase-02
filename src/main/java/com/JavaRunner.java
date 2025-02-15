package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.serviceImpl")

public class JavaRunner {
    public static void main(String[] args) {
        SpringApplication.run(JavaRunner.class, args);
    }
}
