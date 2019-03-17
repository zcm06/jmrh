package com.example.jmrh.dao;

import com.example.jmrh.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    public List<UserRole> queryUserRolesByUserId(Long userId);

    public List<UserRole> queryUserRolesByRoleId(Long roleId);

    public void deleteByUserId(Long userId);
}
