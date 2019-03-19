package com.example.jmrh.entity.vo;

import com.example.jmrh.entity.Address;
import com.example.jmrh.entity.Item;
import com.example.jmrh.entity.TableInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 20:53
 **/
public class TableInfoVo extends TableInfo {

    @Getter
    @Setter
    private List<Item> itemList = new ArrayList<Item>();

    @Getter
    @Setter
    private List<Long> itemIds = new ArrayList<Long>();

    @Getter
    @Setter
    private List<Address> addressList = new ArrayList<>();

    @Getter
    @Setter
    private int page;

    @Getter
    @Setter
    private int size;

    @Getter
    @Setter
    private Date startUnitCreateTime;

    @Getter
    @Setter
    private Date endUnitCreateTime;

    @Getter
    @Setter
    private Double startRegisteredCapital;

    @Getter
    @Setter
    private Double endRegisteredCapital;

    @Getter
    @Setter
    private Double startFixedTotalAssets;

    @Getter
    @Setter
    private Double endFixedTotalAssets;

    @Getter
    @Setter
    private Double startAnnualMainBusinessIncome;

    @Getter
    @Setter
    private Double endAnnualMainBusinessIncome;

    @Getter
    @Setter
    private Double startAnnualRdInvestment;

    @Getter
    @Setter
    private Double endAnnualRdInvestment;

    @Getter
    @Setter
    private Integer startTotalPeople;

    @Getter
    @Setter
    private Integer endTotalPeople;

    @Getter
    @Setter
    private Integer startDevelopersNumber;

    @Getter
    @Setter
    private Integer endDevelopersNumber;
}
