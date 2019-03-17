package com.example.jmrh.utils;

import com.example.jmrh.entity.User;
import com.example.jmrh.entity.vo.UserVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-17 14:53
 **/
public class UserUtil {

    public static UserVo getUser(HttpServletRequest request){
       return (UserVo) request.getSession().getAttribute("user");
    }

    public static void setUser(UserVo userVo, HttpServletRequest request){
        request.getSession().setAttribute("user",userVo);
    }

    public static void removeUser(HttpServletRequest request){
        request.getSession().removeAttribute("user");
    }
}
