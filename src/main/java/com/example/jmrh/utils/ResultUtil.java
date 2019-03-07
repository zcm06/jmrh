package com.example.jmrh.utils;

import com.example.jmrh.entity.ResultMap;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-07 23:07
 **/
public class ResultUtil {

    public static ResultMap successfulResultMap(Object obj){
        ResultMap resultMap = new ResultMap();
        resultMap.setState(true);
        resultMap.setValue(obj);
        return resultMap;
    }

    public static ResultMap failResultMap(String message){
        ResultMap resultMap = new ResultMap();
        resultMap.setState(false);
        resultMap.setMessage(message);
        return resultMap;
    }
}
