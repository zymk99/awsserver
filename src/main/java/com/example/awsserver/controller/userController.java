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
        int[] aa={1,4,3,2,56,6,7,3};
        int[] asa=MySort(aa);
        int x=10;
    }
    public int[] MySort (int[] arr){
        db(arr,0,arr.length-1);
        return arr;
    }
    void db(int[] arr,int begin,int end){
        if(begin>=end){
            return;
        }
        int tmp=arr[begin];
        int _b=begin,_e=end;
        while(begin<end){
            for(;begin<end&& arr[end]>=tmp;end--);
            arr[begin]=arr[end];
            for(;begin<end && arr[begin]<=tmp;begin++);
            arr[end]=arr[begin];
        }
        arr[begin]=tmp;
        db(arr,_b,begin-1);
        db(arr,begin+1,_e);
    }

}
