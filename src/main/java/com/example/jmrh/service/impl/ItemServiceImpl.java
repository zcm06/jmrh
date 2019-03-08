package com.example.jmrh.service.impl;

import com.example.jmrh.dao.ItemRepository;
import com.example.jmrh.entity.Item;
import com.example.jmrh.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-07 22:54
 **/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item save(Item item) {

        return itemRepository.save(item);
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.getOne(id);
    }

    @Override
    public List<Item> getItemsByIds(List<Long> ids) {
        return itemRepository.findAllById(ids);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
