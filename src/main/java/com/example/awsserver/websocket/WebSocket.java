package com.example.awsserver.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/webSocket/link")
public class WebSocket {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 用来记录房间的人数
     */
    private static AtomicInteger onlinePersons = new AtomicInteger(0);

    @OnOpen
    public void open( Session session) throws IOException{        //@PathParam("page") String page,  {page}
        try{
            //长时间连接
            session.setMaxIdleTimeout(3600000);
            //log.info("有用户连接");
        }catch (Exception E){
            log.error("open : "+E.getMessage());
            session.getBasicRemote().sendText("{\"error\":\"error\"}");
        }
    }

    @OnClose
    public void close(Session session)  throws IOException{
        log.info("用户强退");
    }

    //接收消息
    @OnMessage
    public void reveiveMessage(Session session,String message) throws IOException {  //@PathParam("page") String page,
        log.error("收到");
    }

    @OnError
    public void error(Throwable throwable){
        try {
            throw throwable;
        } catch (Throwable e) {
            log.error("未知错误 : "+e.getMessage());
            //log.error("打印 : "+e.toString());
            //e.printStackTrace();
        }
    }
}
