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
        int[] x={100,100,100,-250,-60,-140,-50,-50,100,150};
        int aa=magicTower(x);
        int aas=10;
    }

    public int magicTower(int[] nums) {
        int sum[] =new int[nums.length];
        sum[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            sum[i]=sum[i-1]+nums[i];
        }
        if(sum[sum.length-1]<0){
            return -1;
        }
        return 0;
    }

    int last=-1;
    int num=0;
    int minNum=99999;
    public int magicTower2(int[] nums) {
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]>=0){
                last=i;
                break;
            }
        }
        if(last==-1)return -1;
        db(nums,0,0,0);
        if(minNum==99999){
            minNum=-1;
        }
        return minNum;
    }

    void db(int[] list,int index,int next,int sum){         //next 负数和
        if(sum<0 || (index>last && next+sum<0) ){
            return;
        }
        if(index==list.length-1){
            sum+=list[index];
            if(sum+next>=0 && num<=minNum){
                minNum=num;
            }
            return;
        }
        db(list,index+1,next,sum+list[index]);
        if(list[index]<0){
            num+=1;
            db(list,index+1,next+list[index],sum);
            num-=1;
        }
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