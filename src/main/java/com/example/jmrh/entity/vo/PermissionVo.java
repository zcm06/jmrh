package com.example.jmrh.entity.vo;

import com.example.jmrh.entity.Permission;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 22:03
 **/
public class PermissionVo extends Permission{

    @Setter
    @Getter
    private List<PermissionVo> childList = new ArrayList<>();
}
