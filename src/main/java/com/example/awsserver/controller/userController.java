package com.example.awsserver.controller;

import com.example.awsserver.dao.userDao;
import com.example.awsserver.mapper.userMapper;
import com.example.awsserver.tool.CommonUtils;
import com.example.awsserver.tool.JWTUtil;
import com.example.awsserver.websocket.MyWebSocketClient;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.net.URI;
import java.util.*;
import java.util.List;

@RestController
public class userController {
    @Autowired
    private userMapper userm;
    @PostMapping("/user/login")
    public String login(@RequestBody Map<String,String> map){
        String name=(String)map.get("name");
        String passwd=(String)map.get("passwd");
        passwd= CommonUtils.getMd5(passwd);
        userDao user= userm.userLogin(name,passwd);
        String token="";
        if(user!=null){
            String id=user.getId();
            token=JWTUtil.createToken(id,300);
        }
        return token;
    }


    LinkedList<String> list=new LinkedList<String>();
    //algorithm
    @Test
    public void algorithm() throws Exception{
        String url = "ws://10.238.103.122:9998/webSocket/link";
        int[] aa={2160,1936,3,29,27,5,2503,1593,2,0,16,0,3860,28908,6,2,15,49,6246,1946,23,105,7996,196,0,2,55,457,5,3,924,7268,16,48,4,0,12,116,2628,1468};
        int asa=countPairs(aa);
        int x=10;
    }
    public int countPairs(int[] deliciousness) {
        int c=0,max=1000000007;
        for(int i=0;i<deliciousness.length-1;i++){
            for(int j=i+1;j<deliciousness.length;j++){
                int x=deliciousness[i]+deliciousness[j];
                if( x>0 && ( x & (x-1) )== 0 ){
                    c=(c+1)%1000000007;
                }
            }
        }
        return c;
    }


}
