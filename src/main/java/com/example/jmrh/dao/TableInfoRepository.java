package com.example.jmrh.dao;

import com.example.jmrh.entity.TableInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableInfoRepository extends JpaRepository<TableInfo,Long>, JpaSpecificationExecutor<TableInfo> {

    public TableInfo queryTableInfoById(Long id);

    public int countByCity(String city);

    public List<TableInfo> queryTableInfosByIdIn(List<Long> ids);
}
