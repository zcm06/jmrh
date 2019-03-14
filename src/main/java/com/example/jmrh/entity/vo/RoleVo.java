package com.example.jmrh.entity.vo;

import com.example.jmrh.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 21:54
 **/
public class RoleVo extends Role {

    @Getter
    @Setter
    private List<PermissionVo> permissionList = new ArrayList<>();
}
