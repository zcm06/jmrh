package com.example.jmrh.dao;

import com.example.jmrh.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 22:07
 **/
@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    public List<Permission> queryPermissionsByIdIn(List<Long> permissionIds);
}
