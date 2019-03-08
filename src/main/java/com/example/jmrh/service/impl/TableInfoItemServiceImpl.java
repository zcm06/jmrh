package com.example.jmrh.service.impl;

import com.example.jmrh.dao.TableInfoItemRepository;
import com.example.jmrh.entity.TableInfoItem;
import com.example.jmrh.service.TableInfoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public void batchSave(List<TableInfoItem> tableInfoItemList) {

        tableInfoItemRepository.saveAll(tableInfoItemList);
    }

    @Override
    public TableInfoItem save(TableInfoItem tableInfoItem) {

        return tableInfoItemRepository.save(tableInfoItem);
    }
}
