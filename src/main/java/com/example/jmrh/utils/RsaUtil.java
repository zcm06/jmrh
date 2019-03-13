package com.example.jmrh.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: jmrh
 * @description:
 * @author: ZHANG CANMING
 * @create: 2019-03-13 21:14
 **/
public class RsaUtil {

    private static Map<String,String> keymMap = new HashMap<>();

    static {
        try {
            genKeyPair();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.genKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()),"UTF-8");
        String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()),"UTF-8");

        keymMap.put("publicKey",publicKeyString);
        keymMap.put("privateKey",privateKeyString);
    }
    public static String encode(String data,String publicKey)throws Exception{

        byte[] encrypt = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(encrypt));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = new String(Base64.encodeBase64(cipher.doFinal(data.getBytes("UTF-8"))),"UTF-8");
        return outStr;
    }

    public static String decode(String data,String privateKey)throws Exception{
        byte[] decodeData= Base64.decodeBase64(data);
        byte[] decrypt = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decrypt));
        //RSA解密
        Cipher cipher2 = Cipher.getInstance("RSA");
        cipher2.init(Cipher.DECRYPT_MODE, priKey);
        String decryptStr = new String(cipher2.doFinal(decodeData),"UTF-8");
        return decryptStr;
    }

    public static Map<String,String> getKeymMap(){
        return keymMap;
    }
}
