package com.example.jmrh.dao;

import com.example.jmrh.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 22:05
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    public List<Role> queryRolesByIdIn(List<Long> roleIds);

    public Role queryRoleById(Long id);
}
