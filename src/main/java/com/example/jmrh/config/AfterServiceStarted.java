package com.example.jmrh.config;

import com.example.jmrh.entity.*;
import com.example.jmrh.service.*;
import com.example.jmrh.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-27 22:43
 **/
@Component
public class AfterServiceStarted implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        User user = userService.queryUserByUserName(Admin.getName());
        if (ObjectUtils.isEmpty(user)) {
            User admin = new User();
            admin.setUserName(Admin.getName());
            admin.setPassword(Md5Util.md5(Admin.getPassword()));
            admin = userService.save(admin);
            loadRoleAndPermission(admin);
        }
    }

    private void loadRoleAndPermission(User user) throws Exception {
        Role role = new Role();
        role.setName(Admin.getRoleName());
        role.setRemark("系统超级管理员");
        role = roleService.save(role);
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRoleService.save(userRole);
        List<Permission> permissions = permissionService.findAll();
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (Permission permission : permissions) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permission.getId());
            rolePermission.setRoleId(role.getId());
            rolePermissions.add(rolePermission);
        }
        rolePermissionService.saveRolePermissions(rolePermissions);
    }
}
