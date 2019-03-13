package com.example.jmrh.service;

import com.example.jmrh.entity.User;

public interface UserService {

    public User queryUserByUserName(String userName) throws Exception;
}
