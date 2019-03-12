package com.example.jmrh.service;

import com.example.jmrh.entity.Address;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-12 21:31
 **/
public interface AddressService {

    public Address save(Address address)throws Exception;

    public void batchSave(List<Address> list)throws Exception;

    public List<Address> findAll(Address address)throws Exception;

    public void delete(Long id)throws Exception;

    public void deleteByTableInfoId(Long tableInfoId)throws Exception;
}
