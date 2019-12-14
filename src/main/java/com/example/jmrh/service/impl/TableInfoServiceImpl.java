package com.example.jmrh.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.example.jmrh.dao.TableInfoRepository;
import com.example.jmrh.entity.Address;
import com.example.jmrh.entity.TableInfo;
import com.example.jmrh.entity.TableInfoItem;
import com.example.jmrh.entity.vo.TableInfoVo;
import com.example.jmrh.service.AddressService;
import com.example.jmrh.service.ItemService;
import com.example.jmrh.service.TableInfoItemService;
import com.example.jmrh.service.TableInfoService;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 19:59
 **/
@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Autowired
    private TableInfoRepository tableInfoRepository;

    @Autowired
    private TableInfoItemService tableInfoItemService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private AddressService addressService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TableInfo save(TableInfo tableInfo, List<TableInfoItem> tableInfoItemList) throws Exception {

        tableInfo = tableInfoRepository.save(tableInfo);
        return tableInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TableInfo save(TableInfo tableInfo) throws Exception {
        return tableInfoRepository.save(tableInfo);
    }

    @Override
    public TableInfo queryTableInfoById(Long id) throws Exception {
        return tableInfoRepository.queryTableInfoById(id);
    }

    @Override
    public Page<TableInfo> queryTableInfosByVo(TableInfoVo vo, Pageable pageable,List<Long> tableInfoIds) throws Exception{

        Specification<TableInfo> specification = new Specification<TableInfo>() {
            @Override
            public Predicate toPredicate(Root<TableInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                if (!StringUtils.isEmpty(vo.getCreditCode())){
                    list.add(cb.like(root.get("creditCode"),"%"+vo.getCreditCode()+"%"));
                }
                if (!StringUtils.isEmpty(vo.getUnit())){
                    list.add(cb.like(root.get("unit"),"%"+vo.getUnit()+"%"));
                }
                if (!ObjectUtils.isEmpty(tableInfoIds)){
                    list.add(cb.in(root.get("id")).value(tableInfoIds));
                }
                if (!ObjectUtils.isEmpty(vo.getCreateUserId())){
                    list.add(cb.equal(root.get("createUserId"),vo.getCreateUserId()));
                }

                if (!ObjectUtils.isEmpty(vo.getUnitNature())){
                    list.add(cb.like(root.get("unitNature"),"%"+vo.getUnitNature()+"%"));
                }
                if (!ObjectUtils.isEmpty(vo.getLegalRepresentative())){
                    list.add(cb.like(root.get("legalRepresentative"),"%"+vo.getLegalRepresentative()+"%"));
                }
                if(!ObjectUtils.isEmpty(vo.getStartUnitCreateTime())){
                    list.add(cb.greaterThanOrEqualTo(root.get("unitCreateTime"),vo.getStartUnitCreateTime()));
                }
                if(!ObjectUtils.isEmpty(vo.getEndUnitCreateTime())){
                    list.add(cb.lessThanOrEqualTo(root.get("unitCreateTime"),vo.getEndUnitCreateTime()));
                }

//                if (!ObjectUtils.isEmpty(vo.getStartUnitCreateTime()) && !ObjectUtils.isEmpty(vo.getStartUnitCreateTime())){
//                    list.add(cb.between(root.get("unitCreateTime"),vo.getStartUnitCreateTime(),vo.getEndUnitCreateTime()));
//                }
                if(!ObjectUtils.isEmpty(vo.getStartAnnualMainBusinessIncome())){
                    list.add(cb.greaterThanOrEqualTo(root.get("annualMainBusinessIncome"),vo.getStartAnnualMainBusinessIncome()));
                }
                if(!ObjectUtils.isEmpty(vo.getEndAnnualMainBusinessIncome())){
                    list.add(cb.lessThanOrEqualTo(root.get("annualMainBusinessIncome"),vo.getEndAnnualMainBusinessIncome()));
                }
//                if (!ObjectUtils.isEmpty(vo.getStartAnnualMainBusinessIncome()) && !ObjectUtils.isEmpty(vo.getEndAnnualMainBusinessIncome())){
//                    list.add(cb.between(root.get("annualMainBusinessIncome"),vo.getStartAnnualMainBusinessIncome(),vo.getEndAnnualMainBusinessIncome()));
//                }
                if(!ObjectUtils.isEmpty(vo.getStartRegisteredCapital())){
                    list.add(cb.greaterThanOrEqualTo(root.get("registeredCapital"),vo.getStartRegisteredCapital()));
                }
                if(!ObjectUtils.isEmpty(vo.getEndRegisteredCapital())){
                    list.add(cb.lessThanOrEqualTo(root.get("registeredCapital"),vo.getEndRegisteredCapital()));
                }
//                if (!ObjectUtils.isEmpty(vo.getStartRegisteredCapital()) && !ObjectUtils.isEmpty(vo.getEndRegisteredCapital())){
//                    list.add(cb.between(root.get("registeredCapital"),vo.getStartRegisteredCapital(),vo.getEndRegisteredCapital()));
//                }
                if(!ObjectUtils.isEmpty(vo.getStartFixedTotalAssets())){
                    list.add(cb.greaterThanOrEqualTo(root.get("fixedTotalAssets"),vo.getStartFixedTotalAssets()));
                }
                if(!ObjectUtils.isEmpty(vo.getEndFixedTotalAssets())){
                    list.add(cb.lessThanOrEqualTo(root.get("fixedTotalAssets"),vo.getEndFixedTotalAssets()));
                }
//                if (!ObjectUtils.isEmpty(vo.getStartFixedTotalAssets()) && !ObjectUtils.isEmpty(vo.getEndFixedTotalAssets())){
//                    list.add(cb.between(root.get("fixedTotalAssets"),vo.getStartFixedTotalAssets(),vo.getEndFixedTotalAssets()));
//                }
                if(!ObjectUtils.isEmpty(vo.getStartAnnualRdInvestment())){
                    list.add(cb.greaterThanOrEqualTo(root.get("annualRdInvestment"),vo.getStartAnnualRdInvestment()));
                }
                if(!ObjectUtils.isEmpty(vo.getEndAnnualRdInvestment())){
                    list.add(cb.lessThanOrEqualTo(root.get("annualRdInvestment"),vo.getEndAnnualRdInvestment()));
                }
//                if (!ObjectUtils.isEmpty(vo.getStartAnnualRdInvestment()) && !ObjectUtils.isEmpty(vo.getEndAnnualRdInvestment())){
//                    list.add(cb.between(root.get("annualRdInvestment"),vo.getStartAnnualRdInvestment(),vo.getEndAnnualRdInvestment()));
//                }
//                if (!ObjectUtils.isEmpty(vo.getStartTotalPeople()) && !ObjectUtils.isEmpty(vo.getEndTotalPeople())){
//                    list.add(cb.between(root.get("totalPeople"),vo.getStartTotalPeople(),vo.getEndTotalPeople()));
//                }
                if(!ObjectUtils.isEmpty(vo.getStartTotalPeople())){
                    list.add(cb.greaterThanOrEqualTo(root.get("totalPeople"),vo.getStartTotalPeople()));
                }
                if(!ObjectUtils.isEmpty(vo.getEndTotalPeople())){
                    list.add(cb.lessThanOrEqualTo(root.get("totalPeople"),vo.getEndTotalPeople()));
                }

//                if (!ObjectUtils.isEmpty(vo.getStartDevelopersNumber()) && !ObjectUtils.isEmpty(vo.getEndDevelopersNumber())){
//                    list.add(cb.between(root.get("developersNumber"),vo.getStartDevelopersNumber(),vo.getEndDevelopersNumber()));
//                }

                if(!ObjectUtils.isEmpty(vo.getStartDevelopersNumber())){
                    list.add(cb.greaterThanOrEqualTo(root.get("developersNumber"),vo.getStartDevelopersNumber()));
                }
                if(!ObjectUtils.isEmpty(vo.getEndDevelopersNumber())){
                    list.add(cb.lessThanOrEqualTo(root.get("developersNumber"),vo.getEndDevelopersNumber()));
                }
                if (!ObjectUtils.isEmpty(vo.getBusinessScope())){
                    list.add(cb.like(root.get("businessScope"),"%"+vo.getBusinessScope()+"%"));
                }
                if (!ObjectUtils.isEmpty(vo.getMainProducts())){
                    list.add(cb.like(root.get("mainProducts"),"%"+vo.getMainProducts()+"%"));
                }
                if (!ObjectUtils.isEmpty(vo.getHightechEnterprises())){
                    list.add(cb.equal(root.get("hightechEnterprises"),vo.getHightechEnterprises()));
                }
                if (!ObjectUtils.isEmpty(vo.getRegisteredAddress())){
                    list.add(cb.like(root.get("registeredAddress"),"%"+vo.getRegisteredAddress()+"%"));
                }
                if (!ObjectUtils.isEmpty(vo.getResearchProductionAddress())){
                    list.add(cb.like(root.get("researchProductionAddress"),"%"+vo.getResearchProductionAddress()+"%"));
                }
                if (!ObjectUtils.isEmpty(vo.getContactAddress())){
                    list.add(cb.like(root.get("contactAddress"),"%"+vo.getContactAddress()+"%"));
                }
                if (!ObjectUtils.isEmpty(vo.getCity())){
                    list.add(cb.equal(root.get("city"),vo.getCity()));
                }
                if (!ObjectUtils.isEmpty(vo.getDistrict())){
                    list.add(cb.equal(root.get("district"),vo.getDistrict()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };



       return tableInfoRepository.findAll(specification,pageable);
    }

    @Override
    public int countByCity(String city) throws Exception {
        return tableInfoRepository.countByCity(city);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Long id) throws Exception {
        tableInfoRepository.deleteById(id);
    }


    @Override
    public List<TableInfo> findAll() throws Exception {
        return tableInfoRepository.findAll();
    }

    @Override
    public List<TableInfo> findAll(Address address) throws Exception {
        if (ObjectUtils.isEmpty(address)){
            return findAll();
        }else{
            List<Address> addressList= addressService.findAll(address);
            Set<Long> set = new HashSet<Long>();
            for (Address addr: addressList){
                set.add(addr.getTableInfoId());
            }
            return tableInfoRepository.findAllById(set);
        }
    }

    @Override
    public List<TableInfo> queryTableInfosByIds(List<Long> ids) throws Exception {
        return tableInfoRepository.queryTableInfosByIdIn(ids);
    }

}
