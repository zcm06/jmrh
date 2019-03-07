package com.example.jmrh.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-07 23:08
 **/

public class ResultMap implements Serializable {

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private Object value;

    @Getter
    @Setter
    private boolean state;
}
