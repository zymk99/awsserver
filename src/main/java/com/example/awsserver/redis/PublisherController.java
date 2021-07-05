package com.example.awsserver.redis;

import com.example.awsserver.AwsserverApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

//发布消息
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AwsserverApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PublisherController {
    @Autowired
    private RedisUtils redisUtils;

    @Scheduled(fixedRate = 5000)
    @Test
    public void pubMsg() {
        redisUtils.publish("test1", "消息体12231243213123");
    }
}
