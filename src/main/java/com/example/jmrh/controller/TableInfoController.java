package com.example.jmrh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.jmrh.entity.*;
import com.example.jmrh.entity.vo.TableInfoVo;
import com.example.jmrh.entity.vo.UserVo;
import com.example.jmrh.service.*;
import com.example.jmrh.utils.ResultUtil;
import com.example.jmrh.utils.TableField;
import com.example.jmrh.utils.UserUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
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

    @Autowired
    private ItemService itemService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/saveTableInfo")
    @ResponseBody
    public ResultObject saveTableInfo(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        try {

            List<Map<String, Object>> itemList = (List<Map<String, Object>>) map.get("itemList");
            List<Map<String, Object>> addressList = (List<Map<String, Object>>) map.get("addressList");
            Address registeredAddress = (Address)map.get("registeredAddress");
            clearNotUseValue(map);//清除无效的值
            TableInfo tableInfo = new TableInfo();

            Class infoClass = tableInfo.getClass();
            Field[] fields = infoClass.getDeclaredFields();
            copyValue(tableInfo, map, fields);
            tableInfo.setCreateUserId(UserUtil.getUser(request).getId());
            tableInfo.setCity(registeredAddress.getCity());
            tableInfo.setDistrict(registeredAddress.getDistrict());
//            tableInfo.setCity(UserUtil.getUser(request).getCity());
            tableInfo.setCreateTime(new Date());
            tableInfo = tableInfoService.save(tableInfo);
            List<TableInfoItem> tableInfoItemList = new ArrayList<TableInfoItem>();
            TableInfoItem tableInfoItem = null;
            StringBuilder itemBuilder = null;
            Item item = null;
            for (Map<String, Object> itemMap : itemList) {

                String name = ObjectUtils.nullSafeToString(itemMap.get("name"));
                Field field = infoClass.getDeclaredField(name);
                field.setAccessible(true);
                List<Integer> list = (List<Integer>) itemMap.get("ids");
                itemBuilder = new StringBuilder();
                for (Integer id : list) {
                    tableInfoItem = new TableInfoItem();
                    item = itemService.getItemById(id.longValue());
                    itemBuilder.append(item.getItemName() + ",");
                    tableInfoItem.setTableInfoId(tableInfo.getId());
                    tableInfoItem.setItemId(id.longValue());
                    tableInfoItemList.add(tableInfoItem);
                }
                itemBuilder.replace(itemBuilder.length() - 1, itemBuilder.length(), "");
                field.set(tableInfo, itemBuilder.toString());
            }

            tableInfoItemService.deleteTableInfoItemsByTableInfoId(tableInfo.getId());//先删除
            tableInfoItemService.batchSave(tableInfoItemList);//批量保存

            List<Address> list = new ArrayList<>();
            Address address = null;
            StringBuilder sb = null;

            for (Map<String, Object> addressMap : addressList) {
                address = new Address();
                sb = new StringBuilder();
                loadAddressValue(addressMap, address);
                address.setTableInfoId(tableInfo.getId());
                list.add(address);
                sb.append(address.getCity());
                sb.append(address.getDistrict());
                sb.append(address.getTown());
                sb.append(address.getDetail());
                for (Field field : fields) {
                    if (field.getName().equals(address.getFieldName())) {
                        field.set(tableInfo, sb.toString());
                    }
                }
            }

            addressService.deleteByTableInfoId(tableInfo.getId());//先删除
            addressService.batchSave(list);

            tableInfoService.save(tableInfo);
            return ResultUtil.successfulResultMap("保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("保存失败！");
        }
    }

    private void loadAddressValue(Map<String, Object> addressMap, Address address) {

        address.setCity(ObjectUtils.nullSafeToString(addressMap.get("city")));
        address.setDistrict(ObjectUtils.nullSafeToString(addressMap.get("district")));
        address.setTown(ObjectUtils.nullSafeToString(addressMap.get("town")));
        address.setDetail(ObjectUtils.nullSafeToString(addressMap.get("detail")));
        address.setFieldName(ObjectUtils.nullSafeToString(addressMap.get("fieldName")));

    }

    private void copyValue(TableInfo tableInfo, Map<String, Object> map, Field[] fields) throws Exception {

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = map.get(field.getName());
            if (ObjectUtils.isEmpty(value)) {
                continue;
            }
            if (field.getName().equals("createTime")) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = simpleDateFormat.parse("" + value);
                value = date;
            }
//            if (field.getName().equals("unitCreateTime")) {
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                Date date = simpleDateFormat.parse("" + value);
//                value = date;
//            }
            if (field.getName().equals("createUserId") || field.getName().equals("id")) {
                value = Long.parseLong(value + "");
            }
            if (field.getType().getName().equals("java.lang.Double")) {
                value = Double.parseDouble(value + "");
            } else if (field.getType().getName().equals("java.lang.Integer")) {
                value = Integer.parseInt(value + "");
            }
            field.set(tableInfo, value);
        }
    }

    private void clearNotUseValue(Map<String, Object> map) {
        map.remove("registeredAddress");
        map.remove("researchProductionAddress");
        map.remove("contactAddress");
        map.remove("listing");
        map.remove("capitalComponent");
        map.remove("unitCategory");
        map.remove("industryCategory");
        map.remove("militarySalary");
        map.remove("itemList");
        map.remove("addressList");
    }

    @RequestMapping("/queryTableInfo/{id}")
    @ResponseBody
    public ResultObject queryTableInfo(@PathVariable("id") Long id, HttpServletRequest request) {
        try {

            if (id == null) {
                throw new Exception("参数为空！");
            }
            TableInfo tableInfo = tableInfoService.queryTableInfoById(id);
            if (tableInfo == null) {
                throw new Exception("表单信息不存在！");
            }
            List<TableInfoItem> tableInfoItems = tableInfoItemService.queryTableInfoItemsByTableInfoId(id);
            List<Long> itemIds = new ArrayList<>();

            for (TableInfoItem tableInfoItem : tableInfoItems) {
                itemIds.add(tableInfoItem.getItemId());
            }
            Address address = new Address();
            address.setTableInfoId(id);
            List<Address> addressList = addressService.findAll(address);
            Map<String, Object> map = new HashMap<>();
            map.put("tableInfo", tableInfo);
            map.put("itemIds", itemIds);
            map.put("addressList", addressList);
            return ResultUtil.successfulResultMap(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("获取表单信息失败！" + e.getMessage());
        }
    }

    @RequestMapping("/queryTableInfoList")
    @ResponseBody
    public ResultObject queryTableInfoList(@RequestBody TableInfoVo vo, HttpServletRequest request) {
        try {


            String city = vo.getCity();
            String district= vo.getDistrict();
            UserVo userVo = UserUtil.getUser(request);
            List<UserRole> userRoles = userRoleService.queryUserRolesByUserId(userVo.getId());
            if (ObjectUtils.isEmpty(userRoles)) {
                throw new Exception("暂无权限查看!");
            }
            UserRole userRole = userRoles.get(0);
            Role role = roleService.queryRoleById(userRole.getRoleId());

            if (!role.getName().contains("管理员")) {
//                vo.setCity(userVo.getCity());
                if(!userVo.getCity().equals(city)){
                    throw new Exception("暂无权限查看!");
                }
            }

            List<Long> tableInfoIds = null;
            if (!ObjectUtils.isEmpty(vo.getItemIds())) {
                List<TableInfoItem> tableInfoItems = tableInfoItemService.queryTableInfoItemsByItemsId(vo.getItemIds());
                if (!ObjectUtils.isEmpty(tableInfoItems)) {
                    tableInfoIds = new ArrayList<>();
                    for (TableInfoItem tableInfoItem : tableInfoItems) {
                        tableInfoIds.add(tableInfoItem.getTableInfoId());
                    }
                }
            }

            List<Address> addressList = vo.getAddressList();

            if (!ObjectUtils.isEmpty(addressList)) {
                Class clazz = vo.getClass().getSuperclass();
                Field field = null;
                Field[] fields = clazz.getDeclaredFields();
                StringBuilder sb = null;
                for (Address address : addressList) {
                    field = clazz.getDeclaredField(address.getFieldName());
                    field.setAccessible(true);
                    sb = new StringBuilder();
                    sb.append(StringUtils.isEmpty(address.getCity()) ? "" : address.getCity());
                    sb.append(StringUtils.isEmpty(address.getDistrict()) ? "" : address.getDistrict());
                    sb.append(StringUtils.isEmpty(address.getTown()) ? "" : address.getTown());
                    field.set(vo, StringUtils.isEmpty(sb.toString()) ? null : sb.toString());
                }
            }

            Pageable pageable = PageRequest.of(vo.getPage() - 1, vo.getSize());
            Page<TableInfo> infos = tableInfoService.queryTableInfosByVo(vo, pageable, tableInfoIds);
            return ResultUtil.successfulResultMap(infos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("查询失败！" + e.getMessage());
        }
    }

    @RequestMapping("/queryTableInfosByUser")
    @ResponseBody
    public ResultObject queryTableInfosByUser(@RequestBody TableInfoVo vo, HttpServletRequest request) {
        try {
            User user = UserUtil.getUser(request);
            vo.setCreateUserId(user.getId());
            Pageable pageable = PageRequest.of(vo.getPage() - 1, vo.getSize());
            Page<TableInfo> infos = tableInfoService.queryTableInfosByVo(vo, pageable, null);
            return ResultUtil.successfulResultMap(infos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("查询失败！" + e.getMessage());
        }
    }

    @RequestMapping("/deleteTableInfo")
    @ResponseBody
    public ResultObject queryTableInfosByUser(HttpServletRequest request) {
        try {
            String id = request.getParameter("id");
            tableInfoService.deleteById(Long.parseLong(id));
            return ResultUtil.successfulResultMap("删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failResultMap("删除失败！" + e.getMessage());
        }
    }

    @RequestMapping("/exportData")
    @ResponseBody
    public void exportData(@RequestParam("data") String data, HttpServletRequest request, HttpServletResponse response) {

        try {

            JSONObject jsonObject = JSON.parseObject(data);
            Object obj = jsonObject.get("fields");
            Object obj2 = jsonObject.get("ids");
            List<Long> ids = new ArrayList<>();
            List<String> fields = null;
            if (!ObjectUtils.isEmpty(obj)) {
                fields = (List<String>) obj;
            }

            if (!ObjectUtils.isEmpty(obj2)) {

                List<Integer> list = (List<Integer>) obj2;
                for (Integer integer : list) {
                    ids.add(integer.longValue());
                }
            }
            exportTableData(fields, ids, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void exportTableData(List<String> fields, List<Long> ids, HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<String> captions = new ArrayList<>();
        List<String> fields2 = new ArrayList<>();

        for (TableField anEnum : TableField.values()) {
            if (!ObjectUtils.isEmpty(fields)) {
                for (String field : fields) {
                    if (anEnum.name().equals(field)) {
                        captions.add(anEnum.getValue());
                    }
                }
            } else {
//                fields = new ArrayList<>();
                captions.add(anEnum.getValue());
                fields2.add(anEnum.name().toString());
            }
        }

        if (ObjectUtils.isEmpty(fields)) {
            fields = fields2;
        }

        List<TableInfo> tableInfos = null;

        if (ObjectUtils.isEmpty(ids)) {
            tableInfos = tableInfoService.findAll();
        } else {
            tableInfos = tableInfoService.queryTableInfosByIds(ids);
        }

        String filePath = "军民融合产业企业情况表.xlsx";

        OutputStream outputStream = response.getOutputStream();

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);

        for (int i = 0; i < captions.size(); i++) {
            String value = captions.get(i);
            Cell cell = row.createCell(i);
            cell.setCellValue(value);
        }

        for (int i = 0; i < tableInfos.size(); i++) {
            Row sheetRow = sheet.createRow(i + 1);

            TableInfo tableInfo = tableInfos.get(i);
            Field[] declaredFields = tableInfo.getClass().getDeclaredFields();

            for (int j = 0; j < fields.size(); j++) {
                Cell rowCell = sheetRow.createCell(j);

                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (field.getName().equals(fields.get(j))) {
                        rowCell.setCellValue(ObjectUtils.nullSafeToString(field.get(tableInfo)));
                    }
                }
            }
        }
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filePath, "UTF-8"));
        workbook.write(outputStream);
        outputStream.flush();

    }

}
