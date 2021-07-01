package com.example.awsserver.tool;

import org.springframework.util.DigestUtils;

public class CommonUtils {
    private static final String slat ="whxl2021";
    public static String getMd5(String str){
        String base = str +"/"+slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
