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
        int[][] aa={{3,4},{4,5},{3,2},{5,1},{1,3},{4,2}};
        int[] asa=gardenNoAdj(5,aa);
        int x=10;
    }
    int[] val=null;
    public int[] gardenNoAdj(int n, int[][] paths) {
        val=new int[n];
        int[][] map=new int[n][3];
        for(int i=0;i<paths.length;i++) {
            int min=paths[i][0] >paths[i][1]? paths[i][1]-1:paths[i][0]-1;
            int max=paths[i][0] >paths[i][1]? paths[i][0]-1:paths[i][1]-1;
            int[] curr=map[min];
            for(int c=0;c<3;c++){
                if(curr[c]==0){
                    map[min][c]=max+1;
                    break;
                }
            }
            curr=map[max];
            for(int c=0;c<3;c++){
                if(curr[c]==0){
                    map[max][c]=min+1;
                    break;
                }
            }
        }
        for(int index=0;index<map.length;index++){
            int[] list=map[index];
            int all[]={1,2,3,4};
            if(val[ index ]!=0){
                all[val[index]-1]=0;
            }
            for(int i=0;i<list.length;i++){
                if(list[i]==0)break;
                if(val[list[i]-1]!=0){
                    all[ val[list[i]-1]-1 ]=0;
                }
            }
            int i=0;
            for(;i<list.length;i++){
                if(list[i]==0)break;
                if(val[list[i]-1]==0){
                    int cc=0;
                    while (all[cc]==0)cc++;
                    val[list[i]-1]=all[cc];
                    all[cc]=0;
                }
            }
            if(i==0){
                val[index]=1;
            }
        }
        return val;
    }


}
