package com.example.jmrh.dao;

import com.example.jmrh.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-12 20:33
 **/
public interface AddressRepository extends JpaRepository<Address,Long> {


    public void deleteAddressesByTableInfoId(Long tableInfoId);
}
