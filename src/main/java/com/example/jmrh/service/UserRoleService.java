package com.example.jmrh.service;

import com.example.jmrh.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    public List<UserRole> queryUserRolesByUserId(Long userId)throws Exception;

    public List<UserRole> queryUserRolesByRoleId(Long roleId)throws Exception;

    public void deleteByUserId(Long userId)throws Exception;

    public UserRole save(UserRole userRole)throws Exception;

    public UserRole queryUserRoleByUserIdAndRoleId(Long userId,Long roleId)throws Exception;
}
