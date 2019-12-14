package com.example.jmrh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: jmrh
 * @description: 项目实体
 * @author: ZHANG CANMING
 * @create: 2019-03-07 22:38
 **/

@Entity
@Table(name="jmrh_item")
public class Item implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "item_name",nullable = false)
    private String itemName;

    @Getter
    @Setter
    @Column(name = "item_level")
    private Integer itemLevel;

    @Getter
    @Setter
    @Column(name = "item_type")
    private String itemType;

    @Getter
    @Setter
    @Column(name = "parent_id")
    private Long parentId;

    @Getter
    @Setter
    @Column(name = "field_name")
    private String fieldName;


}
