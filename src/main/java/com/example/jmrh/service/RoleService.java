package com.example.jmrh.service;

import com.example.jmrh.entity.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 22:13
 **/
public interface RoleService {

    public List<Role> queryRolesByIds(List<Long> roleIds)throws Exception;

    public Role save(Role role)throws Exception;

    public void deleteById(Long id)throws Exception;

    public List<Role> findAll()throws Exception;

    public Role queryRoleById(Long id)throws Exception;

    public Role queryRoleByRoleName(String roleName)throws Exception;
}
