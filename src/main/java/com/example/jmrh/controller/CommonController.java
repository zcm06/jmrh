package com.example.jmrh.controller;

import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.entity.User;
import com.example.jmrh.service.UserService;
import com.example.jmrh.utils.Md5Util;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 19:37
 **/
@Controller
@RequestMapping("/commonController")
public class CommonController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getPublicKey")
    @ResponseBody
    public ResultObject getPublicKey(HttpServletRequest request){
        try {
            Map<String,String> map= RsaUtil.getKeymMap();
            return ResultUtil.successfulResultMap(map.get("publicKey"));
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("获取公钥失败！"+e.getMessage());
        }
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public ResultObject addUser(@RequestBody User user,HttpServletRequest request){
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
}
