package com.example.jmrh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jmrh.entity.Item;
import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.service.ItemService;
import com.example.jmrh.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-07 22:56
 **/
@Controller
@RequestMapping("/itemController")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/saveItem")
    @ResponseBody
    public ResultObject saveItem(@RequestParam("itemData") String itemData, HttpServletRequest request){
        try {
            JSONObject jsonObject = JSON.parseObject(itemData);
            Item item = new Item();
            BeanUtils.copyProperties(jsonObject,item);
            itemService.save(item);
            return ResultUtil.successfulResultMap("添加成功！");
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.successfulResultMap("添加失败！"+e.getMessage());
        }
    }
}
