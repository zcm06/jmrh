package com.example.jmrh.service;

import com.example.jmrh.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public User queryUserByUserName(String userName) throws Exception;

    public User save(User user)throws Exception;

    public void deleteUserById(Long id)throws Exception;

    public Page<User> queryUsers(User user, Pageable pageable)throws Exception;

    public User queryUserById(Long id)throws Exception;
}
