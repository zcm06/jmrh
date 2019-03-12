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
    private double startRegisteredCapital;

    @Getter
    @Setter
    private double endRegisteredCapital;

    @Getter
    @Setter
    private double startFixedTotalAssets;

    @Getter
    @Setter
    private double endFixedTotalAssets;

    @Getter
    @Setter
    private double startAnnualMainBusinessIncome;

    @Getter
    @Setter
    private double endAnnualMainBusinessIncome;

    @Getter
    @Setter
    private double startAnnualRdInvestment;

    @Getter
    @Setter
    private double endAnnualRdInvestment;

    @Getter
    @Setter
    private int startTotalPeople;

    @Getter
    @Setter
    private int endTotalPeople;

    @Getter
    @Setter
    private int startDevelopersNumber;

    @Getter
    @Setter
    private int endDevelopersNumber;
}
