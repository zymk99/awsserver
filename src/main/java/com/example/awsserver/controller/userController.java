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
import java.util.concurrent.Future;

@RestController
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AwsserverApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    public void aaa() throws Exception {
        Future<Map> f=a.retuAsy();
        Map M=(Map)f.get();
        Future<Map> f2=a.retuAsy();
        Map M2=(Map)f2.get();
        int aaa=10;
    }

    LinkedList<String> list=new LinkedList<String>();
    //algorithm
    @Test
    public void algorithm() throws Exception{
        int[] x={1};
        String aa=decodeString("2[abc]3[cd]ef");
        int aas=10;
    }

    public String decodeString(String s) {
        String val="";
        while(s.indexOf('[')>=0){
            char[] cs=s.toCharArray();
            for(int i=0;i<cs.length;i++){
                if(cs[i]>='0' && cs[i]<='9'){
                    val+=s.substring(0,i);
                    int n=Integer.parseInt( s.substring(i,s.indexOf('[')) );
                    String ss=null;
                    if(s.indexOf('[',2)>0 && s.indexOf('[',2)<s.indexOf(']')){
                        int num=1,begin=s.indexOf('[');
                        while(num!=0){
                            begin++;
                            if(cs[begin]=='[')num++;
                            if(cs[begin]==']')num--;
                        }
                        ss=decodeString(s.substring(s.indexOf('[')+1,begin));
                        s=s.substring(begin+1,s.length());
                    }else{
                        ss=s.substring(s.indexOf('[')+1,s.indexOf(']'));
                        s=s.substring(s.indexOf(']')+1,s.length());
                    }
                    for(int c=0;c<n;c++){
                        val+=ss;
                    }
                    break;
                }
            }
        }
        val+=s;
        return val;
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