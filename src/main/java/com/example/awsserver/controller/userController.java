package com.example.awsserver.controller;

import com.example.awsserver.dao.userDao;
import com.example.awsserver.mapper.userMapper;
import com.example.awsserver.tool.CommonUtils;
import com.example.awsserver.tool.JWTUtil;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.awt.image.ImageWatched;

import java.util.*;

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
    public void algorithm(){
        int[] a={-1,2,0};
        int k=1;
        int v=findKthLargest(a,k);
        return ;
    }
    public int findKthLargest(int[] list , int k){
        int tmp=list[0];
        int i=0,j=list.length-1;
        while(i<j){
            for(;i<j && list[j]>=tmp;j--);
            list[i]=list[j];
            if(i>=j)break;
            for(;i<j && list[i]<=tmp;i++);
            list[j]=list[i];
        }
        int index=i;
        list[index]=tmp;
        int right=list.length-1-index;
        if(right>=k){
            int[] l=new int[right];
            System.arraycopy(list,index+1,l,0,right);
            return findKthLargest(l,k);
        }else if(right+1==k){
            return tmp;
        }else{
            return findKthLargest(java.util.Arrays.copyOf(list,index),k-(right+1));
        }
    }
    public void db(int begin,int end,char[] cs) {
        if(begin==end){
            list.add(new String(cs));
            return ;
        }
        Set set=new HashSet();
        for(int i=begin;i<=end;i++){
            if(set.contains(cs[i])){
                continue;
            }
            set.add(cs[i]);
            char c=cs[i];
            cs[i]=cs[begin];
            cs[begin]=c;
            db(begin+1,end,cs);
            c=cs[i];
            cs[i]=cs[begin];
            cs[begin]=c;
        }
    }





}
