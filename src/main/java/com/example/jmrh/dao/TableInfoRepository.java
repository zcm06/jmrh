package com.example.jmrh.dao;

import com.example.jmrh.entity.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableInfoRepository extends JpaRepository<TableInfo,Long> {

    public TableInfo queryTableInfoById(Long id);
}
