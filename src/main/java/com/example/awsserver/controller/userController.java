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
        int[][] x={{20,24},{3,17},{17,20},{8,15},{14,17},{6,17},{15,23},{6,8},{15,19},{16,22},{7,9},{8,22},{2,4},{4,11},{22,25},{6,24},{13,19},{15,18},{1,9},{4,9},{4,19},{5,10},{4,21},{4,12},{5,6}};
        int[] aa=findRedundantConnection(x);
        int aas=10;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int max=0;
        for(int[] t:edges){
            max=Math.max( max,Math.max(t[0],t[1]) );
        }
        MAX=max;
        flag=new int[max];
        int[][] map=new int[max][max];
        for(int[] t:edges){
            map[t[0]-1][t[1]-1]=1;
            map[t[1]-1][t[0]-1]=1;
        }
        //找到环形
        int i=0;
        for(;i<max;i++){
            int c=0;
            for(int j=0;j<i;j++){
                if(map[j][i]==1)c++;
            }
            if(c>=2){
                break;
            }
        }
        db(map,i,i);
        for(int j=edges.length-1;j>=0;j--){
            if(set.contains(edges[j][0]) && set.contains(edges[j][1]) )
            {
                return edges[j];
            }
        }
        return null;
    }
    Set set=new HashSet();
    int[] flag=null;
    int MAX=0;
    int flagnum=0;
    boolean F=false;
    boolean db(int[][] map,int poi,int last){
        if(flag[poi]==1){
            flagnum=poi;
            F=true;
            return true;
        }else{
            flag[poi]=1;
        }
        for(int i=0;i<MAX;i++){
            if(i!=last && map[i][poi]==1){
                if( db(map,i,poi) ){
                    if(F){
                        set.add(i+1);
                    }
                    if(poi==flagnum){
                        F=false;
                    }
                    return true;
                }
            }
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