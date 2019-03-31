package com.example.jmrh.aspect;

import com.example.jmrh.entity.Admin;
import com.example.jmrh.entity.User;
import com.example.jmrh.entity.vo.UserVo;
import com.example.jmrh.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-27 22:50
 **/
@Aspect
@Component
public class UserServiceAspect {

    @Autowired
    private UserService userService;

    @Pointcut("execution(public * com.example.jmrh.controller.UserController.deleteUser(..))")
    public void filterDeleteUser(){

    }

    @Pointcut("execution(public * com.example.jmrh.controller.UserController.updateUser(..))")
    public void filterUpdateUser(){

    }

    @Pointcut("execution(public * com.example.jmrh.controller.UserController.updatePassword(..))")
    public void filterUpdatePassword(){

    }


    @Before("filterDeleteUser()")
    public void saveBefore(JoinPoint joinPoint) throws Exception {
        Object[] args= joinPoint.getArgs();
        HttpServletRequest request =(HttpServletRequest) args[0];
        String id = request.getParameter("id");
        User user = userService.queryUserById(Long.parseLong(id));
        if ( !ObjectUtils.isEmpty(user) && Admin.getName().equals(user.getUserName())){
            throw new Exception("不允许删除超级管理员！");
        }

    }

    @Before("filterUpdateUser()")
    public void updateBefore(JoinPoint joinPoint) throws Exception {
        Object[] args= joinPoint.getArgs();
        User user =(User) args[0];
        user = userService.queryUserById(user.getId());
        if (Admin.getName().equals(user.getUserName())){
            throw new Exception("不允许修改超级管理员！");
        }
    }

    @Before("filterUpdatePassword()")
    public void updatePasswordBefore(JoinPoint joinPoint) throws Exception {
        Object[] args= joinPoint.getArgs();
        User user =(User) args[0];
        user = userService.queryUserById(user.getId());
        if (Admin.getName().equals(user.getUserName())){
            throw new Exception("不允许修改超级管理员！");
        }
    }
}
