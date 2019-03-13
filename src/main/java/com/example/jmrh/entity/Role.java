package com.example.jmrh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 20:50
 **/
@Entity
@Table(name = "jmrh_role")
public class Role {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;
}
