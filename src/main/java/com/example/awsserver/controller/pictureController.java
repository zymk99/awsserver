package com.example.awsserver.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/photo")
public class pictureController {
    private String dicPath="";
    private static String[] allPicDic=null;
    private String[] currFiles=null;
    private int currIndex=-1;
    @PostMapping(value="/getPhotoList" ,produces = "application/json;charset=UTF-8")
    public String getPicList(@RequestBody Map map){
        if(allPicDic==null){
            buildAllPicDic();
        }
        int pageNum=Integer.parseInt(map.get("pageNum").toString());  //每页个数
        int pageSize=Integer.parseInt(map.get("pageSize").toString());  //页数
        if( (pageSize-1)*pageNum+1 > allPicDic.length ){
            return null;
        }
        int first=(pageSize-1)*pageNum,last=pageSize*pageNum>allPicDic.length? allPicDic.length: pageSize*pageNum;
        List list=new LinkedList();
        Map value=new HashMap();
        try {
            for (int i = first; i < last; i++) {
                File f = new File(dicPath + "//" + allPicDic[i]);
                String name = (f.list())[0];
                Map M = new HashMap();
                M.put("title", allPicDic[i]);
                M.put("url", getPicBase64(dicPath + "//" + allPicDic[i] + "//" + name));
                M.put("index",i);
                list.add(M);
            }
            value.put("list",list);
            value.put("pageMax",allPicDic.length/pageNum+(allPicDic.length%pageNum>0?1:0) );
            List v2=new LinkedList();
            v2.add(value);
            return JSONObject.toJSONString(v2);
        }catch (Exception e){

        }
        return null;
    }

    @PostMapping(value="/getPhotoByIndex", produces = "application/json;charset=UTF-8")
    public String getPhotoByIndex(@RequestBody Map map){
        int index= Integer.parseInt(map.get("index").toString());
        int _page= Integer.parseInt(map.get("_page").toString());
        if(allPicDic==null){
            buildAllPicDic();
        }
        if(currFiles==null || index!=currIndex){
            currIndex=index;
            buildCurrFile();
        }
        try {
            Map value = new HashMap();
            value.put("lastPage", currFiles.length);
            String base64 = getPicBase64(dicPath + "//" + allPicDic[index] + "//" + currFiles[_page - 1]);
            value.put("url",base64);
            List v2=new LinkedList();
            v2.add(value);
            return JSONObject.toJSONString(v2);
        }catch (Exception e){ }
        return null;
    }

    public void buildAllPicDic(){
        File dic=new File(dicPath);
        File[] diclist= dic.listFiles();
        int c=0;
        for(File tmp:diclist){
            if(tmp.isDirectory())c++;
        }
        allPicDic=new String[c];
        c=0;
        for(File tmp:diclist){
            if(tmp.isDirectory()){
                allPicDic[c++]=tmp.getName();
            }
        }
    }
    public void buildCurrFile(){
        String p=dicPath+"//"+allPicDic[currIndex];
        String[] ps=(new File(p)).list();
        currFiles=new String[ps.length];
        int c=0;
        for(String t:ps){
            currFiles[c++]=t;
        }
    }
    public String getPicBase64(String path) throws Exception {
        InputStream in = new FileInputStream(path);
        byte[] bs= new byte[in.available()];
        in.read(bs);
        in.close();
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Str = encoder.encode(bs);
        return base64Str;
    }
}
