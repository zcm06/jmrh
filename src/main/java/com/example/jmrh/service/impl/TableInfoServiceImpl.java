package com.example.jmrh.service.impl;

import com.example.jmrh.dao.TableInfoItemRepository;
import com.example.jmrh.dao.TableInfoRepository;
import com.example.jmrh.entity.Item;
import com.example.jmrh.entity.TableInfo;
import com.example.jmrh.entity.TableInfoItem;
import com.example.jmrh.service.ItemService;
import com.example.jmrh.service.TableInfoItemService;
import com.example.jmrh.service.TableInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 19:59
 **/
@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Autowired
    private TableInfoRepository tableInfoRepository;

    @Autowired
    private TableInfoItemService tableInfoItemService;

    @Autowired
    private ItemService itemService;

    @Override
    public TableInfo save(TableInfo tableInfo, List<TableInfoItem> tableInfoItemList) {
        Map<String, String> map = new HashMap<String, String>();
        List<Long> itemIds = new ArrayList<Long>();
        if (tableInfoItemList != null && !tableInfoItemList.isEmpty()) {
            for (TableInfoItem tableInfoItem : tableInfoItemList) {
                itemIds.add(tableInfoItem.getItemId());
            }
            tableInfoItemService.batchSave(tableInfoItemList);
        }
        List<Item> itemList = itemService.getItemsByIds(itemIds);
        String fieldName = null;
        String itemName = null;
        for (Item item : itemList) {
            fieldName = item.getFieldName();
            itemName = item.getItemName();
            if (map.get(item.getFieldName()) != null) {
                itemName = map.get(fieldName) + "," + itemName;

            }
            map.put(fieldName, itemName);
        }
        BeanUtils.copyProperties(map, tableInfo);
        tableInfo = tableInfoRepository.save(tableInfo);
        return tableInfo;
    }

    @Override
    public TableInfo save(TableInfo tableInfo) {
        return tableInfoRepository.save(tableInfo);
    }
}
