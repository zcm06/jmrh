package com.example.jmrh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 20:52
 **/
@Entity
@Table(name = "jmrh_user_role")
public class UserRole {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "user_id")
    private Long userId;

    @Getter
    @Setter
    @Column(name = "role_id")
    private Long roleId;
}
