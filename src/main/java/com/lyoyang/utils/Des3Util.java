package com.lyoyang.utils;

import com.google.common.io.BaseEncoding;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Des3Util {

    private static final String ALGORITHM = "DESede";

    private static final String PADDING = "DESede/CBC/PKCS5Padding";

    public static String encrypt3Des(byte[] content, byte[] key) throws Exception {
        byte[] icv = new byte[8];
        System.arraycopy(key, 0, icv, 0, 8);
        return encrypt3Des(content ,key, icv);
    }

    public static String encrypt3Des(byte[] content, byte[] key, byte[] icv) throws Exception {
        final SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        final Cipher cipher = Cipher.getInstance(PADDING);
        final IvParameterSpec spec = new IvParameterSpec(icv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, spec);
        byte[] encryptContent = cipher.doFinal(content);
        return BaseEncoding.base64().encode(encryptContent);
    }



    public static String descrypt3Des(String content, byte[] key) throws Exception {
        byte[] icv = new byte[8];
        System.arraycopy(key, 0, icv, 0, 8);
        return descrypt3Des(content, key, icv);
    }

    public static String descrypt3Des(String content, byte[] key, byte[] icv) throws Exception {
        byte[] decodeContent = BaseEncoding.base64().decode(content);
        final SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        final Cipher cipher = Cipher.getInstance(PADDING);
        final IvParameterSpec spec = new IvParameterSpec(icv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
        byte[] descryptContent = cipher.doFinal(decodeContent);
        return new String(descryptContent, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        String data = "{\"order_id\": \"201609010016562012987\",\"dealer_id\": \"2736123\",\"broker_id\":\n" +
                "\"yiyun\",\"real_name\": \"张三\",\"card_no\": \"6228880199872220\",\"phone_no\":\n" +
                "\"13488795491\",\"id_card\": \"123532612312312321\",\"pay\": \"100000.00\"}";
        String key = "123456788765432112345678";
        String encrypt3Des = encrypt3Des(data.getBytes("utf-8"), key.getBytes("utf-8"));
        System.out.println(encrypt3Des);
        String des = descrypt3Des(encrypt3Des, key.getBytes("utf-8"));
        System.out.println(des);
    }

}
