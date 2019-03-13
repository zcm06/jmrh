package com.example.jmrh.service.impl;

import com.example.jmrh.dao.UserReposity;
import com.example.jmrh.entity.User;
import com.example.jmrh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 20:58
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserReposity userReposity;


    @Override
    public User queryUserByUserName(String userName) throws Exception {

        return userReposity.queryUserByUserName(userName);
    }
}
