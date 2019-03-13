package com.example.jmrh.controller;

import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.RsaUtil;
import org.springframework.stereotype.Controller;
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
}
