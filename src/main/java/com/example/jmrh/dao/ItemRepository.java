package com.example.jmrh.dao;

import com.example.jmrh.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: jmrh
 * @description: 项目repository
 * @author: ZHANG CANMING
 * @create: 2019-03-07 22:50
 **/
@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

}
