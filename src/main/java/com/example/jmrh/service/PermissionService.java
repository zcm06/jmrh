package com.example.jmrh.service;

import com.example.jmrh.entity.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> queryPermissionsByIds(List<Long> id)throws Exception;

    public Permission queryPermissionById(Long id)throws Exception;

    public void deleteById(Long id)throws Exception;
}
