package com.example.awsserver.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class MyWebSocketClient {
    private static Logger logger = LoggerFactory.getLogger(MyWebSocketClient.class);
    public static WebSocketClient client;
    public static void main(String[] args)  throws Exception{
        try {
            client = new WebSocketClient(new URI("ws://localhost:14621"),new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    logger.info("握手成功");
                }

                @Override
                public void onMessage(String msg) {
                    logger.info("收到消息=========="+msg);
                    if(msg.equals("over")){
                        client.close();
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    logger.info("链接已关闭");
                }

                @Override
                public void onError(Exception e){
                    e.printStackTrace();
                    logger.info("发生错误已关闭");
                }
            };
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        client.connect();
        //logger.info(client.getDraft());
        while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
            logger.info("正在连接...");
        }
        //连接成功,发送信息
        client.send("哈喽,连接一下啊");
    }

}
