package com.example.jmrh.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.example.jmrh.entity.Item;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 20:54
 **/
public class ItemVo extends Item {

    @Getter
    @Setter
    private List<ItemVo> childList = new ArrayList<ItemVo>();
}
