package com.example.jmrh.entity.vo;

import com.example.jmrh.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-17 14:20
 **/
public class UserVo extends User {

    @Getter
    @Setter
    List<RoleVo> roleVos = new ArrayList<>();


}
