package com.example.awsserver.controller;

import com.example.awsserver.AwsserverApplication;
import com.example.awsserver.dao.userDao;
import com.example.awsserver.mapper.userMapper;
import com.example.awsserver.tool.CommonUtils;
import com.example.awsserver.tool.JWTUtil;
import com.example.awsserver.websocket.MyWebSocketClient;
import com.fasterxml.jackson.core.util.InternCache;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AwsserverApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @Autowired
    dataController a;
    @Scheduled(fixedRate = 5000)
    @Test
    @PostMapping("/user/test")
    public void aaa() throws InterruptedException {
        for(int i=0;i<5;i++){
            a.asy();
        }
    }

    LinkedList<String> list=new LinkedList<String>();
    //algorithm
    @Test
    public void algorithm() throws Exception{
        int[][] x={{1,2},{2,3},{5},{0},{5},{},{}};
        List<Integer> aa=eventualSafeNodes(x);
        int aas=10;
    }

    int[] map;
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List v=new LinkedList<Integer>();
        map=new int[graph.length];
        for(int i=0;i<graph.length;i++){
            db(graph,i);
        }
        for(int i=0;i<map.length;i++){
            if(map[i]==2){
                v.add(i);
            }
        }
        return v;
    }

    boolean db(int[][] list,int index){
        if(map[index]==1){
            return false;
        }else if(map[index]==2){
            return true;
        }
        map[index]=1;
        for(int x:list[index]){
            if(!db(list,x)){
                return false;
            }
        }
        map[index]=2;
        return true;
    }

    //快排
    void mysort(int[] nums,int a,int b){
        if(a>=b)return;
        int t=nums[a];
        int x=a,y=b;
        while(x<y){
            while(nums[y]>=t && x<y)y--;
            nums[x]=nums[y];
            while(nums[x]<t && x<y)x++;
            nums[y]=nums[x];
        }
        nums[y]=t;
        mysort(nums,a,y-1);
        mysort(nums,y+1,b);
    }

}