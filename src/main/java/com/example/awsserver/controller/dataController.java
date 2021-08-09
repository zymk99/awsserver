package com.example.awsserver.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

@RestController
public class dataController {
    @Async
    public void asy() throws InterruptedException {
        for(int i=0;i<10;i++){
            System.out.println(i);
            Thread.sleep(500);
        }
    }
    @Async
    public Future<Map> retuAsy() throws Exception{
        for(int i=0;i<10;i++){
            System.out.println(i+" "+Thread.currentThread());
            Thread.sleep(500);
        }
        Map m=new HashMap();
        m.put("aa",123);
        return new AsyncResult<Map>(m);
    }
}
