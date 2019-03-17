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
    public Item save(Item item)throws Exception;

    public Item getItemById(Long id)throws Exception;

    public List<Item> getItemsByIds(List<Long> ids)throws Exception;

    public List<Item> getAllItems()throws Exception;

    public void deleteById(Long id)throws Exception;
}
