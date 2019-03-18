package com.example.jmrh.controller;

import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.entity.User;
import com.example.jmrh.entity.vo.UserVo;
import com.example.jmrh.service.UserService;
import com.example.jmrh.utils.Md5Util;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.RsaUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/saveUser")
    @ResponseBody
    public ResultObject addUser(@RequestBody User user, HttpServletRequest request){
        try {
            String userName = user.getUserName();
            User user1 = userService.queryUserByUserName(userName);
            if (user1 != null) {
                throw new Exception("用户名已存在！");
            }
            String password = user.getPassword();
            password = password.replace("%2B", "+");
            String privateKey = RsaUtil.getKeymMap().get("privateKey");
            String decodePassword = RsaUtil.decode(password, privateKey);
            String md5Password = Md5Util.md5(decodePassword);
            user.setPassword(md5Password);
            userService.save(user);
            return ResultUtil.successfulResultMap("新增成功!");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("新增失败！"+e.getMessage());
        }
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public ResultObject deleteUser(HttpServletRequest request){
        try {
            String id = request.getParameter("id");
            userService.deleteUserById(Long.parseLong(id));
            return ResultUtil.successfulResultMap("删除成功!");
        }catch (Exception e){
            return ResultUtil.successfulResultMap("删除失败!");
        }
    }

    @RequestMapping("/queryUsers")
    @ResponseBody
    public ResultObject queryUsers(@RequestBody UserVo userVo,HttpServletRequest request){
        try {
            PageRequest pageRequest = PageRequest.of(userVo.getPage(),userVo.getSize());
            User user = new User();
            BeanUtils.copyProperties(userVo,user);
            return ResultUtil.successfulResultMap(userService.queryUsers(user,pageRequest));
        }catch (Exception e){
            return ResultUtil.successfulResultMap("删除失败!");
        }
    }

    @RequestMapping("/queryUserById")
    @ResponseBody
    public ResultObject queryUserById(HttpServletRequest request){
        try {
            String id = request.getParameter("id");
            return ResultUtil.successfulResultMap(userService.queryUserById(Long.parseLong(id)));
        }catch (Exception e){
            return ResultUtil.successfulResultMap("删除失败!");
        }
    }
}
