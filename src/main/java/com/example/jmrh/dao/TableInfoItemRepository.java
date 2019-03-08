package com.example.jmrh.dao;

import com.example.jmrh.entity.TableInfoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 20:04
 **/
@Repository
public interface TableInfoItemRepository extends JpaRepository<TableInfoItem,Long> {
}
