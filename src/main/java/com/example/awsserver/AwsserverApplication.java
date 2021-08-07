package com.example.awsserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AwsserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsserverApplication.class, args);
    }

}
