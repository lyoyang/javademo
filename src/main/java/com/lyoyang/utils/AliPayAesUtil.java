package com.lyoyang.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

public class AliPayAesUtil {


    public static void main(String[] args) throws Exception {

        String key = "99+B3x6i8Zg79WYt/N04ZQ==";
        String charset = "utf-8";

        String text = encrypt();
        System.out.println(text);
        String decrypt = decrypt(text, key, charset);
        System.out.println(decrypt);
    }


    public static String encrypt() throws Exception {

        String key = "99+B3x6i8Zg79WYt/N04ZQ==";
        String content = "{\"goods_type\":\"1\",\"out_trade_no\":\"201117164212305100000\",\"product_code\":\"FAST_INSTANT_TRADE_PAY\",\"subject\":\"5G消息体验套餐\",\"timeout_express\":\"10m\",\"total_amount\":\"100.00\"}";
        String charset = "utf-8";
        String fullAlg = "AES/CBC/PKCS5Padding";

        Cipher cipher = Cipher.getInstance(fullAlg);
        IvParameterSpec iv = new IvParameterSpec(initIv(fullAlg));
        cipher.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(Base64.decodeBase64(key.getBytes()), "AES"),
                iv);

        byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
        return new String(Base64.encodeBase64(encryptBytes));
    }

    /**
     * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
     *
     * @param fullAlg
     * @return
     * @throws GeneralSecurityException
     */
    private static byte[] initIv(String fullAlg) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance(fullAlg);
        int blockSize = cipher.getBlockSize();
        byte[] iv = new byte[blockSize];
        for (int i = 0; i < blockSize; ++i) {
            iv[i] = 0;
        }
        return iv;
    }


    public static String decrypt(String content, String key, String charset) throws Exception {

        //反序列化AES密钥
        SecretKeySpec keySpec = new SecretKeySpec(Base64.decodeBase64(key.getBytes()), "AES");

        //128bit全零的IV向量
        byte[] iv = new byte[16];
        for (int i = 0; i < iv.length; i++) {
            iv[i] = 0;
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        //初始化加密器并加密
        Cipher deCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
        byte[] encryptedBytes = Base64.decodeBase64(content.getBytes());
        byte[] bytes = deCipher.doFinal(encryptedBytes);
        return new String(bytes);
    }


}
