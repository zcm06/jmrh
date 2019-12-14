package com.example.jmrh.config.intercepors;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.entity.vo.UserVo;
import com.example.jmrh.utils.UserUtil;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 21:03
 **/

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserVo userVo = UserUtil.getUser(request);
        if (userVo == null){
            ajaxResponse(response,request);
            return false;
        }else {
            return true;
        }
    }

    protected void ajaxResponse(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setCharacterEncoding("UTF-8");//Constant.ENCODE_UTF8
        response.setContentType("application/json; charset=utf-8");
        ResultObject object = new ResultObject();
        object.setCode(401);
        object.setState(false);
        object.setMessage("用户未登录！");
        Object json = JSON.toJSON(object);
        PrintWriter out = response.getWriter();
        out.print(json.toString());
        out.flush();
        out.close();
    }

}
