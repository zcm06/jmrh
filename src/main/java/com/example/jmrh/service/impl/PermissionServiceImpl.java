package com.example.jmrh.service.impl;

import com.example.jmrh.dao.PermissionRepository;
import com.example.jmrh.entity.Permission;
import com.example.jmrh.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-25 23:04
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    public List<Permission> queryPermissionsByIds(List<Long> id) throws Exception {
        return permissionRepository.queryPermissionsByIdIn(id);
    }

    @Override
    public Permission queryPermissionById(Long id) throws Exception {
        return permissionRepository.queryPermissionById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) throws Exception {
        permissionRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Permission save(Permission permission) throws Exception {
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionRepository.findAll();
    }
}
