package com.example.jmrh.controller;

import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.entity.Role;
import com.example.jmrh.entity.User;
import com.example.jmrh.entity.UserRole;
import com.example.jmrh.entity.vo.RoleVo;
import com.example.jmrh.entity.vo.UserVo;
import com.example.jmrh.service.RoleService;
import com.example.jmrh.service.UserRoleService;
import com.example.jmrh.service.UserService;
import com.example.jmrh.utils.Md5Util;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.RsaUtil;
import com.example.jmrh.utils.UserUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 19:36
 **/
@Controller
@RequestMapping("/loginController")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultObject login(@RequestBody User user, HttpServletRequest request) {
        try {
            String userName = user.getUserName();
            User user1 = userService.queryUserByUserName(userName);
            if (user1 == null) {
                throw new Exception("用户名或密码错误！");
            }

            String password = user.getPassword();
            password = password.replace("%2B", "+");
            String privateKey = RsaUtil.getKeymMap().get("privateKey");
            String decodePassword = RsaUtil.decode(password, privateKey);
            if (!Md5Util.verify(decodePassword, user1.getPassword())) {
                throw new Exception("用户名或密码错误！");
            }

            user1.setLastLoginTime(new Date());
            userService.save(user1);//更新登录时间
            user1.setPassword("");
            UserVo userVo = setRole(user1);
            UserUtil.setUser(userVo, request);
            return ResultUtil.successfulResultMap("登录成功！");

        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("登录失败！" + e.getMessage());
        }
    }

    private UserVo setRole(User user) throws Exception {
        List<UserRole> userRoles = userRoleService.queryUserRolesByUserId(user.getId());
        List<RoleVo> roleVos = new ArrayList<>();
        List<Long> roleIds = new ArrayList<>();
        if (!ObjectUtils.isEmpty(userRoles)) {
            for (UserRole userRole : userRoles) {
                roleIds.add(userRole.getRoleId());
            }
        }

        if (!ObjectUtils.isEmpty(roleIds)) {
            RoleVo roleVo = null;
            List<Role> roles = roleService.queryRolesByIds(roleIds);
            for (Role role : roles) {
                roleVo = new RoleVo();
                BeanUtils.copyProperties(role, roleVo);
                roleVos.add(roleVo);
            }
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setRoleVos(roleVos);
        return userVo;
    }


    @RequestMapping("/logout")
    @ResponseBody
    public ResultObject logout(HttpServletRequest request) {
        try {
            UserUtil.removeUser(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("注销失败！");
        }
        return ResultUtil.successfulResultMap("注销成功！");
    }

}
