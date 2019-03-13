package com.example.jmrh.controller;

import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.entity.User;
import com.example.jmrh.service.UserService;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/login")
    @ResponseBody
    public ResultObject login(User user, HttpServletRequest request){
        try {
            String userName = user.getUserName();
            User user1 = userService.queryUserByUserName(userName);
            if (user1 == null){
                throw new Exception("用户名或密码错误！");
            }
            String password  = user.getPassword();
            String privateKey = RsaUtil.getKeymMap().get("privateKey");
            String decodePassword = RsaUtil.decode(password,privateKey);
            if (user1.getPassword().equals(decodePassword)){
                request.getSession().setAttribute("user",user1);
                return ResultUtil.successfulResultMap("登录成功！");
            }else {
                throw new Exception("用户名或密码错误！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("登录失败！"+e.getMessage());
        }
    }


}
