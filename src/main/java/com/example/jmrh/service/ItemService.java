package com.example.jmrh.service;

import com.example.jmrh.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {

    /** 
    * @Description: 保存项目
    * @Param: [item] 
    * @return: com.example.jmrh.entity.Item 
    * @Author: ZHANG CANMING
    * @Date: 2019/3/7 
    */ 
    public Item save(Item item);

    public Item getItemById(Long id);

    public List<Item> getItemsByIds(List<Long> ids);

    public List<Item> getAllItems();
}
