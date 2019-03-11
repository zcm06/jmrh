package com.example.jmrh.service.impl;

import com.example.jmrh.dao.TableInfoItemRepository;
import com.example.jmrh.dao.TableInfoRepository;
import com.example.jmrh.entity.Item;
import com.example.jmrh.entity.TableInfo;
import com.example.jmrh.entity.TableInfoItem;
import com.example.jmrh.entity.vo.TableInfoVo;
import com.example.jmrh.service.ItemService;
import com.example.jmrh.service.TableInfoItemService;
import com.example.jmrh.service.TableInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

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
    public TableInfo save(TableInfo tableInfo, List<TableInfoItem> tableInfoItemList) throws Exception {
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
    public TableInfo save(TableInfo tableInfo) throws Exception {
        return tableInfoRepository.save(tableInfo);
    }

    @Override
    public TableInfo queryTableInfoById(Long id) throws Exception {
        return tableInfoRepository.queryTableInfoById(id);
    }

    @Override
    public Page<TableInfo> queryTableInfosByVo(TableInfoVo vo, Pageable pageable,List<Long> tableInfoIds) throws Exception{


        Specification<TableInfo> specification = new Specification<TableInfo>() {
            @Override
            public Predicate toPredicate(Root<TableInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(vo.getCreditCode())){
                    list.add(cb.like(root.get("creditCode"),"%"+vo.getCreditCode()+"%"));
                }
                if (!StringUtils.isEmpty(vo.getUnit())){
                    list.add(cb.like(root.get("unit"),"%"+vo.getUnit()+"%"));
                }
                if (!ObjectUtils.isEmpty(tableInfoIds)){

                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };
       return tableInfoRepository.findAll(specification,pageable);
    }
}
