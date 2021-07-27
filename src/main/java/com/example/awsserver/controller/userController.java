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
        int[] target = {391381350,272779990,14679827,772485354,331478688,673799788,328776406,135016059,894557868,559131299} ;
        int[] arr = {559131299,338063085,559131299,338063085,135016059,793174916,14679827,14679827,894557868,526921048};
        int x=minOperations(target,arr);
        int aa=10;
    }

    public int minOperations(int[] target, int[] arr) {
        int max=0;
        for(int x:target){
            if(x>max)max=x;
        }
        int leng=0;
        if(max<100000){
            int[] map=new int[max+1];
            for(int i=0;i<target.length;i++) {
                map[target[i]] = i + 1;
            }
            for(int i=0;i<arr.length;i++){
                if(max<arr[i] || map[arr[i]]==0){
                    arr[i]=0;
                }else{
                    leng++;
                    arr[i]=map[arr[i]];
                }
            }
        }else{
            Map m=new HashMap<Integer, Integer>();
            for(int i=0;i<target.length;i++){
                m.put(target[i],i+1);
            }
            for(int i=0;i<arr.length;i++){
                if(m.get(arr[i])==null){
                    arr[i]=0;
                }else{
                    leng++;
                    arr[i]=(int)m.get(arr[i]);
                }
            }
        }
        int[] tmp=new int[leng];
        int c=0;
        for(int x:arr){
            if(x!=0){
                tmp[c++]=x;
            }
        }
        int b=bp(tmp);
        return target.length-b;
    }
    public int bp(int[] list){
        int[] bp=new int[list.length];
        int M=0;
        for(int i=0;i<list.length;i++){
            int max=0;
            for(int j=0;j<i;j++){
                if(list[j]<list[i] && bp[j]>max){
                    max=bp[j];
                }
            }
            bp[i]=max+1;
            M= M<bp[i] ? bp[i] : M;
        }
        return M;
    }


}
