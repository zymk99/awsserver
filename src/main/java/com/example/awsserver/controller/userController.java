package com.example.awsserver.controller;

import com.example.awsserver.dao.userDao;
import com.example.awsserver.mapper.userMapper;
import com.example.awsserver.tool.CommonUtils;
import com.example.awsserver.tool.JWTUtil;
import com.example.awsserver.websocket.MyWebSocketClient;
import com.fasterxml.jackson.core.util.InternCache;
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
        int x=longestCommonSubsequence("mhunuzqrkzsnidwbun","szulspmhwpazoxijwbq");
        int aa=10;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        char[] c1=text1.toCharArray(),c2=text2.toCharArray();
        int x=text1.length(),y=text2.length();
        int[][] map=new int[x+1][y+1];
        for(int i=1;i<=x;i++){
            for(int j=1;j<=y;j++){
                if(c1[i-1]==c2[j-1]){
                    map[i][j]=map[i-1][j-1]+1;
                }else{
                    map[i][j]=Math.max(map[i-1][j],map[i][j-1]);
                }
            }
        }
        return map[x][y];
    }

}
