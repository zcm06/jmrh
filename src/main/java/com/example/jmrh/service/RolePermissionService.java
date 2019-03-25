package com.example.jmrh.service;

import com.example.jmrh.entity.RolePermission;

import java.util.List;

public interface RolePermissionService {

    public void deleteByRoleId(Long roleId)throws Exception;

    public void saveRolePermissions(List<RolePermission> list)throws Exception;

    public List<RolePermission> queryRolePermissionsByRoleId(Long roleId)throws Exception;


}
