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

public class ResultObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1351672101727673335L;

	@Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private Object value;

    @Getter
    @Setter
    private boolean state;

    @Getter
    @Setter
    private int code;
}
