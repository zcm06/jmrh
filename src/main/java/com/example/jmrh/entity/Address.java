package com.example.jmrh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-12 20:10
 **/
@Entity
@Table(name = "jmrh_address")
public class Address {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "city")
    private String city;

    @Getter
    @Setter
    @Column(name = "district")
    private String district;

    @Getter
    @Setter
    @Column(name = "town")
    private String town;

    @Getter
    @Setter
    @Column(name = "number")
    private String number;

    @Getter
    @Setter
    @Column(name = "detail")
    private String detail;

    @Getter
    @Setter
    @Column(name = "tableinfo_id")
    private Long tableInfoId;

    @Getter
    @Setter
    @Column(name = "field_name")
    private String fieldName;

}
