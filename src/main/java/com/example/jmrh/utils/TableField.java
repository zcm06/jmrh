package com.example.jmrh.utils;

public enum TableField {

    unit("单位"),creditCode("社会统一信用代码"),unitNature("单位性质"),
    legalRepresentative("法定代表人"),unitCreateTime("单位创建时间"),tel("联系电话"),
    registeredCapital("注册资金"),fixedTotalAssets("固定总资产"),annualMainBusinessIncome("年度主营业务收入"),
    annualRdInvestment("年度研发投入"),totalPeople("总人数"),developersNumber("研发人数"),businessScope("经营范围"),
    mainProducts("主要产品"),registeredAddress("注册地址"),researchProductionAddress("科研地址"),contactAddress("通信地址"),
    hightechEnterprises("高新技术企业"),listing("上市"),capitalComponent("资本成分"),
    unitCategory("单位类别"),industryCategory("行业类别"),militarySalary("军工资质"),unitOverview("单位概况");

    private String value;

    TableField(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
