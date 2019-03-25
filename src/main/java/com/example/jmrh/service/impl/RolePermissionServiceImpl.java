package com.example.jmrh.service.impl;

import com.example.jmrh.dao.RolePermissionRepository;
import com.example.jmrh.entity.RolePermission;
import com.example.jmrh.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-25 22:56
 **/
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByRoleId(Long roleId) throws Exception {
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRolePermissions(List<RolePermission> list) throws Exception {
        rolePermissionRepository.saveAll(list);
    }

    @Override
    public List<RolePermission> queryRolePermissionsByRoleId(Long roleId) throws Exception {
        return rolePermissionRepository.queryRolePermissionsByRoleId(roleId);
    }
}
