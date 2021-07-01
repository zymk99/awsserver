package com.example.awsserver.dao;

import lombok.Data;

@Data
public class userDao {
    private String id;
    private String name;
    private String passwd;
    private String isvalid;
}
