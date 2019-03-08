package com.example.jmrh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 19:35
 **/
@Table(name = "jmrh_tableinfo")
@Entity
public class TableInfo {

    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "create_user_id")
    private Long createUserId;

    @Getter
    @Setter
    @Column(name = "create_time")
    private Date createTime;

    @Getter
    @Setter
    @Column(name = "unit")
    private String unit;

    @Getter
    @Setter
    @Column(name = "credit_code")
    private String creditCode;

    @Getter
    @Setter
    @Column(name = "unit_nature")
    private String unitNature;

    @Getter
    @Setter
    @Column(name = "legal_representative")
    private String legalRepresentative;

    @Getter
    @Setter
    @Column(name = "unit_create_time")
    private Date unitCreateTime;

    @Getter
    @Setter
    @Column(name = "tel")
    private String tel;

    @Getter
    @Setter
    @Column(name = "registered_capital")
    private String registeredCapital;

    @Getter
    @Setter
    @Column(name = "fixed_total_assets")
    private String fixedTotalAssets;

    @Getter
    @Setter
    @Column(name = "annual_main_business_income")
    private String annualMainBusinessIncome;

    @Getter
    @Setter
    @Column(name = "annual_rd_investment")
    private String annualRdInvestment;

    @Getter
    @Setter
    @Column(name = "total_people")
    private int totalPeople;

    @Getter
    @Setter
    @Column(name = "developers_number")
    private int developersNumber;

    @Getter
    @Setter
    @Column(name = "business_scope")
    private String businessScope;

    @Getter
    @Setter
    @Column(name = "main_products")
    private String mainProducts;

    @Getter
    @Setter
    @Column(name = "registered_address")
    private String registeredAddress;

    @Getter
    @Setter
    @Column(name = "research_production_address")
    private String researchProductionAddress;

    @Getter
    @Setter
    @Column(name = "contact_address")
    private String contactAddress;

    @Getter
    @Setter
    @Column(name = "hightech_enterprises")
    private String hightechEnterprises;

    @Getter
    @Setter
    @Column(name = "listing")
    private String listing;

    @Getter
    @Setter
    @Column(name = "capital_component")
    private String capitalComponent;

    @Getter
    @Setter
    @Column(name = "unit_category")
    private String unitCategory;

    @Getter
    @Setter
    @Column(name = "industry_category")
    private String industryCategory;

    @Getter
    @Setter
    @Column(name = "military_salary")
    private String militarySalary;

    @Getter
    @Setter
    @Column(name = "unit_overview")
    private String unitOverview;

    @Getter
    @Setter
    private List<Item> itemList;
}
