package com.example.jmrh.service.impl;

import com.example.jmrh.dao.TableInfoItemRepository;
import com.example.jmrh.entity.TableInfoItem;
import com.example.jmrh.service.TableInfoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 20:06
 **/
@Service
public class TableInfoItemServiceImpl implements TableInfoItemService {

    @Autowired
    private TableInfoItemRepository tableInfoItemRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchSave(List<TableInfoItem> tableInfoItemList)throws Exception {

        for(TableInfoItem tableInfoItem:tableInfoItemList){
            tableInfoItemRepository.save(tableInfoItem);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TableInfoItem save(TableInfoItem tableInfoItem)throws Exception {

        return tableInfoItemRepository.save(tableInfoItem);
    }

    @Override
    public List<TableInfoItem> queryTableInfoItemsByTableInfoId(Long tableInfoId)throws Exception{

        return tableInfoItemRepository.queryTableInfoItemsByTableInfoId(tableInfoId);
    }

    @Override
    public List<TableInfoItem> queryTableInfoItemsByItemsId(List<Long> itemIds) throws Exception {
        return tableInfoItemRepository.queryTableInfoItemsByItemIdIn(itemIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTableInfoItemsByTableInfoId(Long tableInfoId) throws Exception {
        tableInfoItemRepository.deleteByTableInfoId(tableInfoId);
    }
}
