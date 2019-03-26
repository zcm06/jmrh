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

    private static Map<String, String> keymMap = new HashMap<>();

    public static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKNH/1999oR4qrCp85E6AktCZQ00joIM61A7AYuXGpsKUwbEkAl48XRL0d+S99asZEMPCNa090R492oy6T+Zpzor4icPUbtzR6SciW03J7WIPmUYZ9bUbIjTKCAIfjCa4Lgnjo4i9tXCesbLEElVM7jz1UTSNXSqCK2Y9wIs0i9vAgMBAAECgYBoG23OOwhFKpLalWIBRNVVmv3HT61ValtXCmhP0oZaOFj7xbR49a4uEnG8dX6kV8KNpzZhf3zxIZfPFKr3rEacoN4peJU2M9EDVnH7VLbq8RaMwV32wd6eYSfVdX0KGxR2GLRczPrjus6zh/tqLA6Sl9RcrDvrsE4UfPYrThJcoQJBAN5oTe0bR2Ve20zJHPJkzfjP95NFLLC2dsDOH7rf50quj/twOWewQ0f14LUdOBPcd13NTIreEypTF4QnLIqWuhkCQQC78X7pMhxqTRHBCJUjTzPShOaOBtAf4T+Rb/qopGDpYNh1xJTa49vAucSbSsXUXwVDNTd83uLgPTiY9Jdy4HbHAkBvpKhJd8IVLtKi7umq+9MxLSQ4NydB5bYi/tWKt06hfJMmQqVuWVrMyq5r/CjkP21kFuRvaR7xxhgt7IQ9ZYAxAkBW4Niwxaha/0tgty3xx5AGFcdaCg41XnN7MI9nW60s1MbbEcdZtBEGcxlOS+9NL6uIGNaQk5u+2q4KDq7fvKFLAkBswHrxoRmkSX1gu6y7yUVl5e77gKN2zB0sjPkV3NVcqnV2Xt9TfV+6WHONjM/Ol7Z5eHxK3TTEW81QD27A0yHp";

        static {
        try {
            keymMap.put("privateKey",privateKey);
//            genKeyPair();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//
//    private static void genKeyPair() throws Exception {
//        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
//        keyPairGen.initialize(1024);
//        KeyPair keyPair = keyPairGen.genKeyPair();
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//
//        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()),"UTF-8");
//        String privateKeyString = new String(Base64.encodeBase64(privateKey.getEncoded()),"UTF-8");
//
//        keymMap.put("publicKey",publicKeyString);
//        keymMap.put("privateKey",privateKeyString);
//    }
    public static String encode(String data, String publicKey) throws Exception {

        byte[] encrypt = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(encrypt));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
//        String outStr = new String(Base64.encodeBase64(cipher.doFinal(data.getBytes("UTF-8"))),"UTF-8");
        return Base64.encodeBase64String(cipher.doFinal(data.getBytes("UTF-8")));
    }

    public static String decode(String data, String privateKey) throws Exception {
        byte[] decodeData = Base64.decodeBase64(data);
        byte[] decrypt = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decrypt));
        //RSA解密
        Cipher cipher2 = Cipher.getInstance("RSA");
        cipher2.init(Cipher.DECRYPT_MODE, priKey);
        String decryptStr = new String(cipher2.doFinal(decodeData), "UTF-8");
        return decryptStr;
    }

    public static Map<String, String> getKeymMap() {


        return keymMap;
    }
}
