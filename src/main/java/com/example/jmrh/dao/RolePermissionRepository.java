package com.example.jmrh.dao;

import com.example.jmrh.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 22:06
 **/
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> {

    public List<RolePermission> queryRolePermissionsByRoleId(Long roleId);
}
