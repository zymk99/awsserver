package com.example.awsserver.redis;

import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
//接收订阅的消息
@Component
public class ReceiverRedisMessage {
    private static final Logger log = LoggerFactory.getLogger(ReceiverRedisMessage.class);
    private CountDownLatch latch;

    @Autowired
    public ReceiverRedisMessage(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 队列消息接收方法
     *
     * @param jsonMsg
     */
    public void receiveMessage(String jsonMsg) {
        log.info("[开始消费REDIS消息队列phone数据...]");
        try {
            log.info("监听者收到消息：{}", jsonMsg);
            log.info("[消费REDIS消息队列phone数据成功.]");
        } catch (Exception e) {
            log.error("[消费REDIS消息队列phone数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }
}
