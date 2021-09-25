package com.example.awsserver.controller;

import com.example.awsserver.AwsserverApplication;
import com.example.awsserver.dao.userDao;
import com.example.awsserver.mapper.userMapper;
import com.example.awsserver.tool.CommonUtils;
import com.example.awsserver.tool.JWTUtil;
import com.example.awsserver.websocket.MyWebSocketClient;
import com.fasterxml.jackson.core.util.InternCache;
import lombok.Data;
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

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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

    @Data
    class cla{
        private int a;
        private int b;
        public cla(int x,int y){
            a=x;
            b=y;
        }
    }
    LinkedList<String> list=new LinkedList<String>();
    //algorithm
    @Test
    public void algorithm() throws Exception{
        List<cla> list=new ArrayList<>();
        list.add(new cla(1,2));
        list.add(new cla(1,3));
        list.add(new cla(1,4));
        list.add(new cla(2,2));
        list.add(new cla(3,2));
        list.add(new cla(4,2));

        //去重
        List<cla> v=list.stream().collect(
                collectingAndThen(
                        toCollection(()-> new TreeSet<>(Comparator.comparing(cla::getA))),
                        ArrayList::new
                )
        );
        //修改元素
        list.stream().forEach(val->{
            val.setB(5);
        });
        //过滤
        List<cla> v2=new LinkedList<cla>();
        list.stream().filter(val->{
            if(val.getA()!=1)return true;
            return false;
        }).forEach(vv->{
            v2.add(vv);
        });
        int xx=10;
    }
    String[] ss=null;
    public boolean isValidSerialization(String preorder) {
        ss=preorder.split(",");
        if(db()){
            if(index==ss.length-1){
                return true;
            }
        }
        return false;
    }
    int index=0;
    public boolean db(){
        if(index>=ss.length){
            return false;
        }else if(ss[index].equals("#")){
            return true;
        }
        index++;
        if( db() )
        {
            index++;
            return db();
        }
        return false;
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
