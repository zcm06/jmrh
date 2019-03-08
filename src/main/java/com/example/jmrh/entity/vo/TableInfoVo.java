package com.example.jmrh.entity.vo;

import com.example.jmrh.entity.Item;
import com.example.jmrh.entity.TableInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 20:53
 **/
public class TableInfoVo extends TableInfo {

    @Getter
    @Setter
    private List<Item> itemList = new ArrayList<Item>();
}
