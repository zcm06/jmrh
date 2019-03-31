package com.example.jmrh.service.impl;

import com.example.jmrh.dao.RoleRepository;
import com.example.jmrh.entity.Role;
import com.example.jmrh.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-17 14:10
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> queryRolesByIds(List<Long> roleIds) throws Exception {
        return roleRepository.queryRolesByIdIn(roleIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role save(Role role) throws Exception {
        return roleRepository.save(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) throws Exception {
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> findAll() throws Exception {
        return roleRepository.findAll();
    }

    @Override
    public Role queryRoleById(Long id) throws Exception {
        return roleRepository.queryRoleById(id);
    }

    @Override
    public Role queryRoleByRoleName(String roleName) throws Exception {
        return roleRepository.queryRoleByName(roleName);
    }
}
