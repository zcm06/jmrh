package com.example.jmrh.service.impl;

import com.example.jmrh.dao.UserRoleRepository;
import com.example.jmrh.entity.UserRole;
import com.example.jmrh.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-16 11:08
 **/
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<UserRole> queryUserRolesByUserId(Long userId) throws Exception{
        return userRoleRepository.queryUserRolesByUserId(userId);
    }

    @Override
    public List<UserRole> queryUserRolesByRoleId(Long roleId) throws Exception{
        return userRoleRepository.queryUserRolesByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByUserId(Long userId) throws Exception {
        userRoleRepository.deleteByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserRole save(UserRole userRole) throws Exception {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole queryUserRoleByUserIdAndRoleId(Long userId, Long roleId) throws Exception {
        return userRoleRepository.queryUserRoleByUserIdAndRoleId(userId,roleId);
    }
}
