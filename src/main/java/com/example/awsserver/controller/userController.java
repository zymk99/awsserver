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

import java.awt.*;
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
    public void algorithm(){
        int[] a={-1,0,1,2,-1,-4};
        List<List<Integer>> v=threeSum(a);
        return ;
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List value=new LinkedList();
        List list=new LinkedList();
        for( int x: nums){
            list.add(x);
        }
        Collections.sort(list);
        Object[] os=list.toArray();
        Set s1=new HashSet();
        for(int i=0;i<os.length-2;i++){
            for(int j=i+1;j<os.length-1;j++){
                for(int z=j+1;z<os.length;z++){
                    if((int)os[i]+(int)os[j]+(int)os[z]==0){
                        List t1=new LinkedList();
                        t1.add((int)os[i]);
                        t1.add((int)os[j]);
                        t1.add((int)os[z]);
                        value.add(t1);
                    }
                }
            }
        }
        return value;
    }

}
