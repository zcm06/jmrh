package com.example.jmrh.utils;

import com.example.jmrh.entity.ResultObject;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-07 23:07
 **/
public class ResultUtil {

    public static ResultObject successfulResultMap(Object obj){
        ResultObject resultObject = new ResultObject();
        resultObject.setState(true);
        resultObject.setValue(obj);
        return resultObject;
    }

    public static ResultObject failResultMap(String message){
        ResultObject resultObject = new ResultObject();
        resultObject.setState(false);
        resultObject.setMessage(message);
        return resultObject;
    }
}
