package com.example.jmrh.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 21:59
 **/
@Entity
@Table(name = "jmrh_role_permission")
public class RolePermission {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "role_id")
    private Long roleId;

    @Getter
    @Setter
    @Column(name = "permission_id")
    private Long permissionId;
}
