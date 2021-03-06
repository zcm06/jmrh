package com.example.jmrh.service;

import java.util.List;

import com.example.jmrh.entity.TableInfoItem;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 20:05
 **/
public interface TableInfoItemService {

    public void batchSave(List<TableInfoItem> tableInfoItemList)throws Exception;

    public TableInfoItem save(TableInfoItem tableInfoItem)throws Exception;

    public List<TableInfoItem> queryTableInfoItemsByTableInfoId(Long tableInfoId)throws Exception;

    public List<TableInfoItem> queryTableInfoItemsByItemsId(List<Long> itemIds)throws Exception;

    public void deleteTableInfoItemsByTableInfoId(Long tableInfoId)throws Exception;
}
