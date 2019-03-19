package com.example.jmrh.service.impl;

import com.example.jmrh.dao.TableInfoItemRepository;
import com.example.jmrh.dao.TableInfoRepository;
import com.example.jmrh.entity.Address;
import com.example.jmrh.entity.Item;
import com.example.jmrh.entity.TableInfo;
import com.example.jmrh.entity.TableInfoItem;
import com.example.jmrh.entity.vo.TableInfoVo;
import com.example.jmrh.service.ItemService;
import com.example.jmrh.service.TableInfoItemService;
import com.example.jmrh.service.TableInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;

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

    @Override
    public TableInfo save(TableInfo tableInfo, List<TableInfoItem> tableInfoItemList) throws Exception {

        tableInfo = tableInfoRepository.save(tableInfo);
        return tableInfo;
    }

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
                if (!ObjectUtils.isEmpty(vo.getStartUnitCreateTime()) && !ObjectUtils.isEmpty(vo.getEndUnitCreateTime())){
                    list.add(cb.between(root.get("unitCreateTime"),vo.getStartUnitCreateTime(),vo.getEndUnitCreateTime()));
                }
                if (!ObjectUtils.isEmpty(vo.getStartAnnualMainBusinessIncome()) && !ObjectUtils.isEmpty(vo.getEndAnnualMainBusinessIncome())){
                    list.add(cb.between(root.get("annualMainBusinessIncome"),vo.getStartAnnualMainBusinessIncome(),vo.getEndAnnualMainBusinessIncome()));
                }
                if (!ObjectUtils.isEmpty(vo.getStartRegisteredCapital()) && !ObjectUtils.isEmpty(vo.getEndRegisteredCapital())){
                    list.add(cb.between(root.get("registeredCapital"),vo.getStartRegisteredCapital(),vo.getEndRegisteredCapital()));
                }
                if (!ObjectUtils.isEmpty(vo.getStartFixedTotalAssets()) && !ObjectUtils.isEmpty(vo.getEndFixedTotalAssets())){
                    list.add(cb.between(root.get("fixedTotalAssets"),vo.getStartFixedTotalAssets(),vo.getEndFixedTotalAssets()));
                }
                if (!ObjectUtils.isEmpty(vo.getStartAnnualRdInvestment()) && !ObjectUtils.isEmpty(vo.getEndAnnualRdInvestment())){
                    list.add(cb.between(root.get("annualRdInvestment"),vo.getStartAnnualRdInvestment(),vo.getEndAnnualRdInvestment()));
                }
                if (!ObjectUtils.isEmpty(vo.getStartTotalPeople()) && !ObjectUtils.isEmpty(vo.getEndTotalPeople())){
                    list.add(cb.between(root.get("totalPeople"),vo.getStartTotalPeople(),vo.getEndTotalPeople()));
                }
                if (!ObjectUtils.isEmpty(vo.getStartDevelopersNumber()) && !ObjectUtils.isEmpty(vo.getEndDevelopersNumber())){
                    list.add(cb.between(root.get("developersNumber"),vo.getStartDevelopersNumber(),vo.getEndDevelopersNumber()));
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
                return cb.and(list.toArray(new Predicate[list.size()]));
            }
        };



       return tableInfoRepository.findAll(specification,pageable);
    }

    @Override
    public int countByCity(String city) throws Exception {
        return tableInfoRepository.countByCity(city);
    }


}
