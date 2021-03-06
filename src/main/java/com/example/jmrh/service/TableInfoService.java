package com.example.jmrh.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.jmrh.entity.Address;
import com.example.jmrh.entity.TableInfo;
import com.example.jmrh.entity.TableInfoItem;
import com.example.jmrh.entity.vo.TableInfoVo;

public interface TableInfoService {

    public TableInfo save(TableInfo tableInfo, List<TableInfoItem> tableInfoItemList)throws Exception;

    public TableInfo save(TableInfo tableInfo)throws Exception;

    public TableInfo queryTableInfoById(Long id)throws Exception;

    public Page<TableInfo> queryTableInfosByVo(TableInfoVo vo, Pageable pageable,List<Long> tableInfoIds) throws Exception;

    public int countByCity(String city)throws Exception;

    public void deleteById(Long id)throws Exception;

    public List<TableInfo> findAll()throws Exception;

    public List<TableInfo> findAll(Address address)throws Exception;

    public List<TableInfo> queryTableInfosByIds(List<Long> ids)throws Exception;
}
