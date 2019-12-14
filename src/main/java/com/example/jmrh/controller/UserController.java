package com.example.jmrh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.entity.User;
import com.example.jmrh.entity.UserRole;
import com.example.jmrh.entity.vo.UserVo;
import com.example.jmrh.service.UserRoleService;
import com.example.jmrh.service.UserService;
import com.example.jmrh.utils.Md5Util;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.RsaUtil;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-17 18:52
 **/
@Controller
@RequestMapping("/userController")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/saveUser")
    @ResponseBody
    public ResultObject addUser(@RequestBody UserVo userVo, HttpServletRequest request) {
        try {
            String userName = userVo.getUserName();
            User user1 = userService.queryUserByUserName(userName);
            if (user1 != null && !user1.getId().equals(userVo.getId())) {
                throw new Exception("用户名已存在！");
            }
            String password = userVo.getPassword();
            password = password.replace("%2B", "+");
            String privateKey = RsaUtil.getKeymMap().get("privateKey");
            String decodePassword = RsaUtil.decode(password, privateKey);
            String md5Password = Md5Util.md5(decodePassword);
            userVo.setPassword(md5Password);

            User user = new User();
            BeanUtils.copyProperties(userVo, user);

            user = userService.save(user);

            UserRole userRole = new UserRole();

            userRole.setRoleId(userVo.getRoleId());
            userRole.setUserId(user.getId());
            userRoleService.save(userRole);

            return ResultUtil.successfulResultMap("保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("保存失败！" + e.getMessage());
        }
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public ResultObject updatePassword(@RequestBody User user, HttpServletRequest request) {
        try {
            String password = user.getPassword();
            password = password.replace("%2B", "+");
            String privateKey = RsaUtil.getKeymMap().get("privateKey");
            String decodePassword = RsaUtil.decode(password, privateKey);
            String md5Password = Md5Util.md5(decodePassword);
            User user1 = userService.queryUserById(user.getId());
            user1.setPassword(md5Password);
            userService.save(user1);
            return ResultUtil.successfulResultMap("保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("保存失败！" + e.getMessage());
        }
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultObject updateUser(@RequestBody User user, HttpServletRequest request) {
        try {
            User user1 = userService.queryUserById(user.getId());
            BeanUtils.copyProperties(user, user1);
            userService.save(user1);
            return ResultUtil.successfulResultMap("修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("修改失败！" + e.getMessage());
        }
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResultObject deleteUser(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            userService.deleteUserById(Long.parseLong(id));
            return ResultUtil.successfulResultMap("删除成功!");
        } catch (Exception e) {
            return ResultUtil.successfulResultMap("删除失败!");
        }
    }

    @RequestMapping("/queryUsers")
    @ResponseBody
    public ResultObject queryUsers(@RequestBody UserVo userVo, HttpServletRequest request) {
        try {
            PageRequest pageRequest = PageRequest.of(userVo.getPage() - 1, userVo.getSize());
            User user = new User();
            BeanUtils.copyProperties(userVo, user);
            Page<User> users = userService.queryUsers(user, pageRequest);
            for (User user1 : users.getContent()) {
                user1.setPassword("");
            }
            return ResultUtil.successfulResultMap(users);
        } catch (Exception e) {
            return ResultUtil.successfulResultMap("查询失败!");
        }
    }

    @RequestMapping("/queryUserById")
    @ResponseBody
    public ResultObject queryUserById(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            return ResultUtil.successfulResultMap(userService.queryUserById(Long.parseLong(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.successfulResultMap("删除失败!");
        }
    }


    @RequestMapping("/queryRoleIdByUserId")
    @ResponseBody
    public ResultObject queryRoleIdByUserId(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            Long userId = Long.parseLong(id);
            List<UserRole> userRoles = userRoleService.queryUserRolesByUserId(userId);
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", ObjectUtils.isEmpty(userRoles) ? null : userRoles.get(0).getRoleId());

            return ResultUtil.successfulResultMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.successfulResultMap("查询失败!");
        }
    }
}
