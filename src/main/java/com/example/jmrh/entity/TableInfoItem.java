package com.example.jmrh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-08 19:57
 **/
@Entity
@Table(name = "jmrh_tableinfo_item")
public class TableInfoItem {

    @Getter
    @Setter
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "item_id")
    private Long itemId;

    @Getter
    @Setter
    @Column(name = "tableinfo_id")
    private Long tableInfoId;
}
