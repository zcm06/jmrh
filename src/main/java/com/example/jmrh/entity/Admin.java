package com.example.jmrh.entity;

import lombok.Getter;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-25 23:53
 **/
public class Admin {

    @Getter
    private static final String name = "admin";

    @Getter
    private static final String password = "jmrh_admin";

    @Getter
    private static final String roleName = "超级管理员";

    @Getter
    private static final Long id = Integer.valueOf(Integer.MAX_VALUE/10).longValue();

}
