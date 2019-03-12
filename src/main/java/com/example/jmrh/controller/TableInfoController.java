package com.example.jmrh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.jmrh.entity.Address;
import com.example.jmrh.entity.ResultObject;
import com.example.jmrh.entity.TableInfo;
import com.example.jmrh.entity.TableInfoItem;
import com.example.jmrh.entity.vo.TableInfoVo;
import com.example.jmrh.service.AddressService;
import com.example.jmrh.service.TableInfoItemService;
import com.example.jmrh.service.TableInfoService;
import com.example.jmrh.utils.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @program: jmrh
 * @description: 表单信息controller
 * @author: ZHANG CANMING
 * @create: 2019-03-08 19:30
 **/
@Controller
@RequestMapping("/tableInfoController")
public class TableInfoController {


    @Autowired
    private TableInfoService tableInfoService;

    @Autowired
    private TableInfoItemService tableInfoItemService;

    @Autowired
    private AddressService addressService;

    @RequestMapping("/saveTableInfo")
    @ResponseBody
    public ResultObject saveTableInfo(@RequestParam("tableInfoData") String tableInfoData, HttpServletRequest request) {
        try {
            TableInfo tableInfo = new TableInfo();
            JSONObject jsonObject = JSON.parseObject(tableInfoData);
            BeanUtils.copyProperties(jsonObject, tableInfo);
            tableInfo = tableInfoService.save(tableInfo);
            JSONArray itemList = jsonObject.getJSONArray("itemList");
            JSONArray addressList = jsonObject.getJSONArray("addressList");
            Iterator<Object> iterator = itemList.iterator();
            TableInfoItem tableInfoItem = null;

            List<TableInfoItem> tableInfoItemList = new ArrayList<TableInfoItem>();
            while (iterator.hasNext()) {
                tableInfoItem = new TableInfoItem();
                String itemId = (String) iterator.next();
                tableInfoItem.setTableInfoId(tableInfo.getId());
                tableInfoItem.setItemId(Long.parseLong(itemId));
                tableInfoItemList.add(tableInfoItem);
            }

            tableInfoItemService.deleteTableInfoItemsByTableInfoId(tableInfo.getId());
            tableInfoService.save(tableInfo, tableInfoItemList);
            Address address = null;
            Map<String,String> map = null;
            List<Address> list = new ArrayList<>();
            for (Object obj:addressList) {
                address = new Address();
                map = ( Map<String,String>) obj;
                address.setTableInfoId(tableInfo.getId());
                BeanUtils.copyProperties(map,address);
                list.add(address);
            }
            addressService.deleteByTableInfoId(tableInfo.getId());
            addressService.batchSave(list);
            return ResultUtil.successfulResultMap("");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("保存失败！");
        }
    }

    @RequestMapping("/queryTableInfo/{id}")
    @ResponseBody
    public ResultObject queryTableInfo(@PathVariable("id") Long id, HttpServletRequest request){
        try {

            if (id == null){
                throw new Exception("参数为空！");
            }
            TableInfo tableInfo = tableInfoService.queryTableInfoById(id);
            if (tableInfo == null){
                throw new Exception("表单信息不存在！");
            }
            List<TableInfoItem> tableInfoItems= tableInfoItemService.queryTableInfoItemsByTableInfoId(id);
            List<Long> itemIds = new ArrayList<>();

            for (TableInfoItem tableInfoItem:tableInfoItems){
                itemIds.add(tableInfoItem.getItemId());
            }
            Map<String,Object> map = new HashMap<>();
            map.put("tableInfo",tableInfo);
            map.put("itemIds",itemIds);
            return ResultUtil.successfulResultMap(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("获取表单信息失败！"+e.getMessage());
        }
    }

    @RequestMapping("/queryTableInfoList")
    @ResponseBody
    public ResultObject queryTableInfoList(TableInfoVo vo,HttpServletRequest request){
        try {
            List<Long> tableInfoIds = null;
            if (!ObjectUtils.isEmpty(vo.getItemIds())){
                List<TableInfoItem> tableInfoItems= tableInfoItemService.queryTableInfoItemsByItemsId(vo.getItemIds());
                if (!ObjectUtils.isEmpty(tableInfoItems)){
                    tableInfoIds = new ArrayList<>();
                    for (TableInfoItem tableInfoItem:tableInfoItems){
                        tableInfoIds.add(tableInfoItem.getTableInfoId());
                    }
                }
            }

            Pageable pageable = PageRequest.of(vo.getPage(),vo.getSize());
            Page<TableInfo> infos= tableInfoService.queryTableInfosByVo(vo,pageable,tableInfoIds);
            return  ResultUtil.successfulResultMap(infos);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.failResultMap("查询失败！"+e.getMessage());
        }
    }

}
