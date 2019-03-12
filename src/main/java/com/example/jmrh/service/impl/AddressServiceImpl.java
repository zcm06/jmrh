package com.example.jmrh.service.impl;

import com.example.jmrh.dao.AddressRepository;
import com.example.jmrh.entity.Address;
import com.example.jmrh.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-12 21:32
 **/
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address save(Address address) throws Exception {

        return addressRepository.save(address);
    }

    @Override
    public void batchSave(List<Address> list) throws Exception {
        addressRepository.saveAll(list);
    }

    @Override
    public List<Address> findAll(Address address) throws Exception {
        return addressRepository.findAll(Example.of(address));
    }

    @Override
    public void delete(Long id) throws Exception {
        addressRepository.deleteById(id);
    }

    @Override
    public void deleteByTableInfoId(Long tableInfoId)throws Exception{
        addressRepository.deleteAddressesByTableInfoId(tableInfoId);
    }
}
