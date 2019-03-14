package com.example.jmrh.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-14 22:20
 **/
public class Md5Util {

    public static String md5(String text) throws Exception{

        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(text.getBytes("UTF-8"));
        byte[] secretBytes = digest.digest();
        //将加密后的数据转换为16进制数字
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    /**
    * @Description:
    * @Param: 待验证的字符串
     * @Param: 验证字符串
    * @return: boolean
    * @Author: ZHANG CANMING
    * @Date: 2019/3/14
    */
    public static boolean verify(String data,String code) throws Exception {
        String md5Str = md5(data);
        return md5Str.equals(code);
    }
}
