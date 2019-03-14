package com.example.jmrh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 21:54
 **/
@Entity
@Table(name = "jmrh_permission")
public class Permission {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private int order;

    @Getter
    @Setter
    @Column(name = "parent_id")
    private Long parentId;

    @Getter
    @Setter
    @Column(name = "permission_level")
    private int permissionLevel;
}
