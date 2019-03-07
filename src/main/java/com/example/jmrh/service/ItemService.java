package com.example.jmrh.service;

import com.example.jmrh.entity.Item;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {

    /** 
    * @Description: 保存项目
    * @Param: [item] 
    * @return: com.example.jmrh.entity.Item 
    * @Author: ZHANG CANMING
    * @Date: 2019/3/7 
    */ 
    public Item save(Item item);
}
