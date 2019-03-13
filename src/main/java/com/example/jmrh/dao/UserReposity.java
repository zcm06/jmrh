package com.example.jmrh.dao;

import com.example.jmrh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 20:54
 **/
public interface UserReposity extends JpaRepository<User,Long> {

    public User queryUserByUserName(String userName);
}
