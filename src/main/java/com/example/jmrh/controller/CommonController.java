package com.example.jmrh.controller;

import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.service.TableInfoService;
import com.example.jmrh.utils.CommonUtil;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.RsaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private TableInfoService tableInfoService;

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

    @RequestMapping("/getMapData")
    @ResponseBody
    public ResultObject getMapData(HttpServletRequest request){
        try {

            List<Map<String,Object>> mapList = new ArrayList<>();
            int count = 0;
            for (CommonUtil.Area area:CommonUtil.Area.values()){
                Map<String,Object> map = new HashMap<>();
                count = tableInfoService.countByCity(area.toString());
                map.put("name",area.toString());
                map.put("value",count);
                mapList.add(map);
            }
            return ResultUtil.successfulResultMap(mapList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("获取地图数据失败！");
        }
    }


}
