package com.example.jmrh.dao;

import com.example.jmrh.entity.TableInfoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 20:04
 **/
@Repository
public interface TableInfoItemRepository extends JpaRepository<TableInfoItem,Long> {


    public List<TableInfoItem> queryTableInfoItemsByTableInfoId(Long tableInfoId);

    public List<TableInfoItem> queryTableInfoItemsByItemId(Long itemId);

    public List<TableInfoItem> queryTableInfoItemsByItemIdIn(List<Long> itemIds);

    public void deleteByTableInfoId(Long tableInfoId);
}
