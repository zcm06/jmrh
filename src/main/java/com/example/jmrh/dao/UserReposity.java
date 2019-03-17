package com.example.jmrh.dao;

import com.example.jmrh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 20:54
 **/
public interface UserReposity extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    public User queryUserByUserName(String userName);
}
