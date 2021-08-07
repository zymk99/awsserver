package com.example.awsserver.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dataController {
    @Async
    public void asy() throws InterruptedException {
        for(int i=0;i<10;i++){
            System.out.println(i);
            Thread.sleep(500);
        }
    }
}
