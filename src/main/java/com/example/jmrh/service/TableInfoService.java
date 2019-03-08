package com.example.jmrh.service;

import com.example.jmrh.entity.TableInfo;
import com.example.jmrh.entity.TableInfoItem;

import java.util.List;

public interface TableInfoService {

    public TableInfo save(TableInfo tableInfo, List<TableInfoItem> tableInfoItemList);

    public TableInfo save(TableInfo tableInfo);
}
