package com.example.jmrh.controller;

import com.example.jmrh.entity.Item;
import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.entity.vo.ItemVo;
import com.example.jmrh.service.ItemService;
import com.example.jmrh.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public ResultObject saveItem(@RequestBody Item item, HttpServletRequest request) {
        try {
            if (item.getParentId() != null) {
                Item parent = itemService.getItemById(item.getParentId());
                item.setFieldName(parent.getFieldName());
                item.setItemLevel(parent.getItemLevel() + 1);
            } else {
                item.setItemLevel(1);
            }
            item = itemService.save(item);
            return ResultUtil.successfulResultMap(item);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("添加失败！");
        }
    }

    @RequestMapping("/loadItemList")
    @ResponseBody
    public ResultObject loadItemList(HttpServletRequest request) {
        try {
//            List<ItemVo> itemVoList = new ArrayList<ItemVo>();
            List<Item> itemList = itemService.getAllItems();
//            loadItems(itemVoList, itemList);
            List<ItemVo> itemVoList = loadChild(itemList);
            return ResultUtil.successfulResultMap(itemVoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("初始化节点失败！");
        }
    }

//    private void loadItems(List<ItemVo> itemVoList, List<Item> itemList) {
//        ItemVo itemVo = null;
//        for (Item item : itemList) {
//            if (item.getItemLevel() == 1) {
//                itemVo = new ItemVo();
//                BeanUtils.copyProperties(item, itemVo);
//                itemVoList.add(itemVo);
//                appendChild(itemVo, itemList);
//            }
//        }
//
//
//    }
//
//    private void appendChild(ItemVo itemVo, List<Item> itemList) {
//        ItemVo child = null;
//        for (Item item : itemList) {
//            if (item.getParentId() != null && item.getParentId().equals(itemVo.getId())) {
//                child = new ItemVo();
//                BeanUtils.copyProperties(item, child);
//                itemVo.getChildList().add(child);
//                appendChild(child, itemList);
//            }
//        }
//    }

    /**
     * @Description: 加载子节点
     * @Param: [itemList]
     * @return: java.util.List<com.example.jmrh.entity.vo.ItemVo>
     * @Author: ZHANG CANMING
     * @Date: 2019/3/9
     */
    private List<ItemVo> loadChild(List<Item> itemList) {

        List<ItemVo> itemVoList = new ArrayList<>();
        ItemVo itemVo = null;
        for (Item item : itemList) {
            itemVo = new ItemVo();
            BeanUtils.copyProperties(item, itemVo);
            itemVoList.add(itemVo);
        }
        if (itemVoList.size() <= 1) {
            return itemVoList;
        }

        for (ItemVo vo : itemVoList) {
            for (ItemVo child : itemVoList) {
                if (vo.getId().equals(child.getParentId())) {
                    vo.getChildList().add(child);
                }
            }
        }

        Iterator<ItemVo> iterator = itemVoList.iterator();
        while (iterator.hasNext()) {
            ItemVo next = iterator.next();
            if (next.getParentId() != null) {
                iterator.remove();
            }
        }
        return itemVoList;
    }
}
