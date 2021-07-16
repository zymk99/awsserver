package com.example.awsserver.redis;

import com.example.awsserver.AwsserverApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AwsserverApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Component
public class redisMessQueue {
    private String key="userlist";
    @Autowired
    RedisConnectionFactory mConnectionFactory;

    private StringRedisTemplate redisTemplate;
    private RedisConnection connection;
    @PostConstruct
    public void postInit() {
        connection=mConnectionFactory.getConnection();
        redisTemplate = new StringRedisTemplate(mConnectionFactory);
    }

    @Test
    public void push(){
        redisTemplate.opsForList().leftPush(key, "12213dsadsa");
    }
    @Test
    public void pop(){
        List<byte[]> s = connection.bRPop(300,key.getBytes());
        if(CollectionUtils.isEmpty(s)){
            return ;
        }
        int xx=10;
    }

}
