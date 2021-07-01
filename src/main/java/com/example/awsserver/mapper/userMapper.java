package com.example.awsserver.mapper;

import com.example.awsserver.dao.userDao;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

import java.util.Map;

@Mapper
@Component
public interface userMapper {
    public userDao userLogin(String name, String passwd);
}
