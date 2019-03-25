package com.example.jmrh.utils;

import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-25 21:17
 **/
public class BeanUtil<T> {

    public void copyValue(Map<String,Object> map, T t) throws Exception{

        Class clazz = t.getClass();
        Field[]  fields= clazz.getDeclaredFields();
        for (Field field:fields){
            field.setAccessible(true);
            Object value = field.get(t);
            if (ObjectUtils.isEmpty(value)){
                continue;
            }
            if(field.getName().toLowerCase().contains("time")){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = simpleDateFormat.format(value);
            }
            map.put(field.getName(),value);
        }
    }
}
